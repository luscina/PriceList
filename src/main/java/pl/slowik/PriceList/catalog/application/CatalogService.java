package pl.slowik.PriceList.catalog.application;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.slowik.PriceList.catalog.application.port.CatalogUseCase;
import pl.slowik.PriceList.catalog.db.JpaModelRepository;
import pl.slowik.PriceList.catalog.db.JpaNotebookRepository;
import pl.slowik.PriceList.catalog.domain.ComponentModel;
import pl.slowik.PriceList.catalog.domain.Model;
import pl.slowik.PriceList.catalog.domain.Notebook;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
@Slf4j
class CatalogService implements CatalogUseCase {
    private final JpaNotebookRepository jpaNotebookRepository;
    private final JpaModelRepository modelRepository;

    public Optional<Notebook> findById(Long id) {
        return jpaNotebookRepository.findById(id);
    }

    @Override
    public List<Notebook> findAll() {
        return jpaNotebookRepository.findAll();
    }

    public void updateNotebookPrice(UpdateNotebookPriceCommand command) {
        jpaNotebookRepository
                .findById(command.getId())
                .map(notebook -> updatePrice(notebook, command))
                .orElseThrow();
    }

    private Notebook updatePrice(Notebook notebook, UpdateNotebookPriceCommand command) {
        notebook.setBpPrice(BigDecimal.valueOf(command.getBpPrice()));
        notebook.setBpPricePln(BigDecimal.valueOf(command.getPbPricePln()));
        notebook.setSrpPrice(BigDecimal.valueOf(command.getSrpPrice()));
        jpaNotebookRepository.save(notebook);
        return notebook;
    }

    public void deleteById(Long id){
        jpaNotebookRepository.deleteById(id);
    }

    public Set<ComponentModel> findCompileComponents(Long id){
        String pn = jpaNotebookRepository.findById(id).stream()
                .map(Notebook::getPn)
                .findFirst()
                .orElseThrow();
        String modelCode = pn.substring(0, 4);
        Model model = modelRepository.findByPn(modelCode)
                .stream()
                .findAny()
                .orElseThrow();
        return model.getComponentModelSet();
    }
}
