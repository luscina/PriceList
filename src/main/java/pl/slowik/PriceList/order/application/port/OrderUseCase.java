package pl.slowik.PriceList.order.application.port;

import lombok.Singular;
import lombok.Value;
import pl.slowik.PriceList.order.domain.Order;
import pl.slowik.PriceList.order.domain.Recipient;

import java.io.IOException;
import java.util.List;

public interface OrderUseCase {
    void placeOrder(PlaceOrderCommand command);
    void orderToExcel(Order order) throws IOException;
    @Value
    class PlaceOrderCommand {
        @Singular
        List<OrderItemCommand> items;
        Recipient recipient;
    }
    @Value
    class OrderItemCommand{
        String itemId;
        int quantity;
    }
}
