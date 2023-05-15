package pl.slowik.PriceList.order.web;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.slowik.PriceList.order.application.port.OrderUseCase;
import pl.slowik.PriceList.order.application.port.OrderUseCase.PlaceOrderCommand;

@RestController
@AllArgsConstructor
@RequestMapping("/order")
public class OrderController {
    private final OrderUseCase orderUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void createOrder(@RequestBody PlaceOrderCommand command){
        orderUseCase.placeOrder(command);
    }
}
