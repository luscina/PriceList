package pl.slowik.PriceList.catalog.application.port;

import pl.slowik.PriceList.catalog.domain.Notebook;

import java.util.List;
import java.util.Optional;

public interface CatalogUseCase {
    public Optional<Notebook> findById(Long id);
    public List<Notebook> findAll();
}
