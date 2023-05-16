package pl.slowik.PriceList.order.web;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.slowik.PriceList.order.application.port.OrderUseCase;
import pl.slowik.PriceList.order.application.port.OrderUseCase.PlaceOrderCommand;
import pl.slowik.PriceList.order.db.JpaOrderRepository;
import pl.slowik.PriceList.order.domain.Order;

import java.util.List;

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
    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }
}
