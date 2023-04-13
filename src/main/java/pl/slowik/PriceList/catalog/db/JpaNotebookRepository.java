package pl.slowik.PriceList.catalog.db;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.slowik.PriceList.catalog.domain.Notebook;

import java.util.Optional;

public interface JpaNotebookRepository extends JpaRepository<Notebook, Long> {
    Optional<Notebook> findByPn(String pn);
}
