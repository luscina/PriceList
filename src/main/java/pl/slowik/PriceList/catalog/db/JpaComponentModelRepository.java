package pl.slowik.PriceList.catalog.db;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.slowik.PriceList.catalog.domain.ComponentModel;

public interface JpaComponentModelRepository extends JpaRepository<ComponentModel, Long> {
}
