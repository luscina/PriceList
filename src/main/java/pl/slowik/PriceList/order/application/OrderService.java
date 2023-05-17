package pl.slowik.PriceList.order.application;

import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import pl.slowik.PriceList.catalog.db.JpaNotebookRepository;
import pl.slowik.PriceList.catalog.domain.Notebook;
import pl.slowik.PriceList.order.application.port.OrderUseCase;
import pl.slowik.PriceList.order.db.JpaOrderItemRepository;
import pl.slowik.PriceList.order.db.JpaOrderRepository;
import pl.slowik.PriceList.order.db.JpaRecipientRepository;
import pl.slowik.PriceList.order.domain.Order;
import pl.slowik.PriceList.order.domain.OrderItem;
import pl.slowik.PriceList.order.domain.Recipient;

import javax.transaction.Transactional;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService implements OrderUseCase {
    private final JpaOrderRepository orderRepository;
    private final JpaNotebookRepository notebookRepository;
    private final JpaRecipientRepository recipientRepository;
    private final JpaOrderItemRepository orderItemRepository;

    @Override
    public void placeOrder(PlaceOrderCommand command) {
        Set<OrderItem> items = command
                .getItems()
                .stream()
                .map(this::toOrderItem)
                .collect(Collectors.toSet());
        Order order = Order
                .builder()
                .orderItems(items)
                .recipient(getOrCreateRecipient(command.getRecipient()))
                .build();
        orderRepository.save(order);
    }

    public void orderToExcel(Order order) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Oder #" + order.getId());
        Set<OrderItem> orderItems = order.getOrderItems();
        createHeadersRow(sheet);
        fillOrderFile(sheet, orderItems);
        try(FileOutputStream outputStream = new FileOutputStream("Order " + order.getId() + ".xlsx")){
            workbook.write(outputStream);
        }
    }

    private static void fillOrderFile(XSSFSheet sheet, Set<OrderItem> orderItems) {
        int rowNumber = 1;
        for(OrderItem item : orderItems){
            Row row = sheet.createRow(rowNumber++);
            Cell itemNumber = row.createCell(0);
            itemNumber.setCellValue(rowNumber - 1);
            Notebook notebook = item.getNotebook();
            Cell cellWithPn = row.createCell(1);
            cellWithPn.setCellValue(notebook.getPn());
            Cell cellWithProductFamily = row.createCell(2);
            cellWithProductFamily.setCellValue(notebook.getProductFamily());
            Cell cellWithQuantity = row.createCell(3);
            cellWithQuantity.setCellValue(item.getQuantity());
        }
    }

    private static void createHeadersRow(XSSFSheet sheet) {
        Row headersRow = sheet.createRow(0);
        Cell headerIndex = headersRow.createCell(0);
        Cell headerPnCell = headersRow.createCell(1);
        Cell headerProductFamilyCell = headersRow.createCell(2);
        Cell headerQuantityCell = headersRow.createCell(3);
        headerIndex.setCellValue("Index");
        headerPnCell.setCellValue("PN");
        headerProductFamilyCell.setCellValue("Product Family");
        headerQuantityCell.setCellValue("Quantity");
    }

    private OrderItem toOrderItem(OrderItemCommand command){
        Notebook notebook = notebookRepository.findById(Long.parseLong(command.getItemId())).orElseThrow();
        int quantity = command.getQuantity();
        return new OrderItem(notebook, quantity);
    }

    private Recipient getOrCreateRecipient(Recipient recipient) {
        Recipient returnedRecipient = recipientRepository
                .findByEmailIgnoreCase(recipient.getEmail())
                .orElse(recipient);
        recipientRepository.save(recipient);
        return returnedRecipient;
    }
}
