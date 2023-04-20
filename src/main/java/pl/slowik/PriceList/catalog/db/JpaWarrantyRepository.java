package pl.slowik.PriceList.catalog.db;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.slowik.PriceList.catalog.domain.Warranty;

public interface JpaWarrantyRepository extends JpaRepository<Warranty, Long> {
}
