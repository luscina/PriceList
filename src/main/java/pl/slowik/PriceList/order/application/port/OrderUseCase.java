package pl.slowik.PriceList.order.application.port;

import lombok.Singular;
import lombok.Value;
import pl.slowik.PriceList.order.domain.Recipient;

import java.util.List;

public interface OrderUseCase {
    void placeOrder(PlaceOrderCommand command);
    @Value
    class PlaceOrderCommand {
        @Singular
        List<OrderItemCommand> items;
        Recipient recipient;
    }
    @Value
    class OrderItemCommand{
        Long itemId;
        int quantity;
    }
}
