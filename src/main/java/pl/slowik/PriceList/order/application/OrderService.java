package pl.slowik.PriceList.order.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.slowik.PriceList.order.application.port.OrderUseCase;
import pl.slowik.PriceList.order.db.JpaOrderRepository;
import pl.slowik.PriceList.order.domain.Order;
import pl.slowik.PriceList.order.domain.OrderItem;
import pl.slowik.PriceList.order.domain.Recipient;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService implements OrderUseCase {
    private final JpaOrderRepository orderRepository;

    @Override
    public void placeOrder(PlaceOrderCommand command) {
        Set<OrderItem> items = command
                .getItems()
                .stream().map(this::toOrderItem)
                .collect(Collectors.toSet());
        Recipient recipient = command.getRecipient();
        Order order = new Order(items, recipient);
        orderRepository.save(order);
    }

    private OrderItem toOrderItem(OrderItemCommand command){
        Long itemId = command.getItemId();
        int quantity = command.getQuantity();
        return new OrderItem();
    }
}
