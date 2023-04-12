package pl.slowik.PriceList.catalog.db;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.slowik.PriceList.catalog.domain.Component;

import java.util.Optional;

public interface JpaComponentRepository extends JpaRepository<Component, Long> {
    Optional<Component> findByPn(String pn);
}
