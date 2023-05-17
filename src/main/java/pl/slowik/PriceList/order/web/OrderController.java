package pl.slowik.PriceList.order.web;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.slowik.PriceList.catalog.domain.Notebook;
import pl.slowik.PriceList.order.application.port.OrderUseCase;
import pl.slowik.PriceList.order.application.port.OrderUseCase.PlaceOrderCommand;
import pl.slowik.PriceList.order.db.JpaOrderRepository;
import pl.slowik.PriceList.order.domain.Order;
import pl.slowik.PriceList.order.domain.OrderItem;
import pl.slowik.PriceList.order.domain.Recipient;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/order")
public class OrderController {
    private final OrderUseCase orderUseCase;
    private final JpaOrderRepository orderRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void createOrder(@RequestBody PlaceOrderCommand command){
        orderUseCase.placeOrder(command);
    }
    @GetMapping
    public List<RestOrder> getAllOrders(){
        return orderRepository.findAll()
                .stream()
                .map(this::toRestOrder)
                .collect(Collectors.toList());
    }

    private RestOrder toRestOrder(Order order){
        Set<RestOrderItem> orderItems = order.getOrderItems()
                .stream()
                .map(this::toRestOrderItem)
                .collect(Collectors.toSet());
        RestRecipient restRecipient = toRestRecipient(order.getRecipient());
        return new RestOrder(
                orderItems,
                restRecipient
        );
    }

    private RestOrderItem toRestOrderItem(OrderItem item){
        return new RestOrderItem(
                toRestOrderNotebook(item.getNotebook()),
                item.getQuantity())
        ;
    }

    private RestOrderNotebook toRestOrderNotebook(Notebook notebook){
        return new RestOrderNotebook(
                notebook.getPn(),
                notebook.getProductFamily(),
                notebook.getBpPrice()
        );
    }
    private RestRecipient toRestRecipient(Recipient recipient){
        return new RestRecipient(recipient.getEmail());
    }
}
