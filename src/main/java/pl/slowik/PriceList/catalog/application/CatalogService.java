package pl.slowik.PriceList.catalog.application;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import pl.slowik.PriceList.catalog.application.port.CatalogUseCase;
import pl.slowik.PriceList.catalog.db.JpaNotebookRepository;
import pl.slowik.PriceList.catalog.domain.Notebook;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
class CatalogService implements CatalogUseCase {
    private final JpaNotebookRepository jpaNotebookRepository;

    public Optional<Notebook> findById(Long id) {
        return jpaNotebookRepository.findById(id);
    }

    @Override
    public List<Notebook> findAll() {
        return jpaNotebookRepository.findAll();
    }
}
