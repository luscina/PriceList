package pl.slowik.PriceList.catalog.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import pl.slowik.PriceList.catalog.domain.Model;
import pl.slowik.PriceList.catalog.domain.Warranty;

import java.util.Set;

public interface JpaWarrantyRepository extends JpaRepository<Warranty, Long> {
    Set<Warranty> findByModel(Model model);
}
