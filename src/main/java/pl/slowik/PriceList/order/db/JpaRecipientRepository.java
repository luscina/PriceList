package pl.slowik.PriceList.order.db;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.slowik.PriceList.order.domain.Recipient;

public interface JpaRecipientRepository extends JpaRepository<Recipient, Long> {
}
