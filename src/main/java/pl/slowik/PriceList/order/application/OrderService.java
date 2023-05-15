package pl.slowik.PriceList.order.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.slowik.PriceList.catalog.db.JpaNotebookRepository;
import pl.slowik.PriceList.catalog.domain.Notebook;
import pl.slowik.PriceList.order.application.port.OrderUseCase;
import pl.slowik.PriceList.order.db.JpaOrderRepository;
import pl.slowik.PriceList.order.domain.Order;
import pl.slowik.PriceList.order.domain.OrderItem;
import pl.slowik.PriceList.order.domain.Recipient;
import pl.slowik.PriceList.users.db.JpaUserRepository;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService implements OrderUseCase {
    private final JpaOrderRepository orderRepository;
    private final JpaNotebookRepository notebookRepository;
    private final JpaUserRepository userRepository;

    @Override
    public void placeOrder(PlaceOrderCommand command) {
        Set<OrderItem> items = command
                .getItems()
                .stream().map(this::toOrderItem)
                .collect(Collectors.toSet());
        Order order = Order
                .builder()
                .orderItems(items)
                .recipient(command.getRecipient())
                .build();
        orderRepository.save(order);
    }

    private OrderItem toOrderItem(OrderItemCommand command){
        Notebook notebook = notebookRepository.findById(command.getItemId()).orElseThrow();
        int quantity = command.getQuantity();
        return new OrderItem(notebook, quantity);
    }

}
