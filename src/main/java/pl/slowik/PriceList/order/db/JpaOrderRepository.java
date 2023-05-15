package pl.slowik.PriceList.order.db;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.slowik.PriceList.order.domain.Order;

public interface JpaOrderRepository extends JpaRepository<Order, Long> {
}
