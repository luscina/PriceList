package pl.slowik.PriceList.catalog.db;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.slowik.PriceList.catalog.domain.ComponentModel;
import pl.slowik.PriceList.catalog.domain.Model;

import java.util.Optional;
import java.util.Set;

public interface JpaComponentModelRepository extends JpaRepository<ComponentModel, Long> {
    Set<ComponentModel> findByModel(Model model);
}
