package pl.slowik.PriceList.catalog.db;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.slowik.PriceList.catalog.domain.Model;

import java.util.Optional;

public interface JpaModelRepository extends JpaRepository<Model, Long> {
}
