package pl.slowik.PriceList.order.db;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.slowik.PriceList.order.domain.Recipient;

import java.util.Optional;

public interface JpaRecipientRepository extends JpaRepository<Recipient, Long> {
    Optional<Recipient> findByEmailIgnoreCase(String email);
}
