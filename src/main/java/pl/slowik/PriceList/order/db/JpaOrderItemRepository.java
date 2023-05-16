package pl.slowik.PriceList.order.db;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.slowik.PriceList.order.domain.Order;
import pl.slowik.PriceList.order.domain.OrderItem;

import java.util.Set;

public interface JpaOrderItemRepository extends JpaRepository<OrderItem, Long> {
}
