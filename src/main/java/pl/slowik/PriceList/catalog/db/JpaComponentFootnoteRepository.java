package pl.slowik.PriceList.catalog.db;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.slowik.PriceList.catalog.domain.ComponentFootnote;

public interface JpaComponentFootnoteRepository extends JpaRepository<ComponentFootnote, Long> {
}
