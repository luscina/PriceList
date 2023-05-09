package pl.slowik.PriceList.catalog.application;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.slowik.PriceList.catalog.application.port.CatalogUseCase;
import pl.slowik.PriceList.catalog.db.JpaComponentModelRepository;
import pl.slowik.PriceList.catalog.db.JpaModelRepository;
import pl.slowik.PriceList.catalog.db.JpaNotebookRepository;
import pl.slowik.PriceList.catalog.db.JpaWarrantyRepository;
import pl.slowik.PriceList.catalog.domain.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
class CatalogService implements CatalogUseCase {
    private final JpaNotebookRepository jpaNotebookRepository;
    private final JpaModelRepository modelRepository;
    private final JpaComponentModelRepository componentModelRepository;
    private final JpaWarrantyRepository warrantyRepository;

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

    public void deleteById(Long id) {
        jpaNotebookRepository.deleteById(id);
    }

    public Set<Component> findCompileComponents(Long id) {
        Model model = getModel(id);
        return componentModelRepository.findByModel(model)
                .stream()
                .map(ComponentModel::getComponent)
                .collect(Collectors.toSet());
    }
    public Set<Component> findCompileMemory(Long notebookId) {
        Model model = getModel(notebookId);
        return componentModelRepository.findByModel(model)
                .stream()
                .map(ComponentModel::getComponent)
                .filter(component -> component.getSubCategory().contains("Memory"))
                .collect(Collectors.toSet());
    }

    @Override
    public List<Warranty> findAllWarranties() {
        return warrantyRepository.findAll();
    }

    @Override
    public List<String> findWarrantiesDescriptions() {
        return warrantyRepository.findAll().stream()
                .map(Warranty::getDescription)
                .collect(Collectors.toList());
    }

    public Set<Warranty> findCompileWarranties(Long id) {
        Model model = getModel(id);
        return warrantyRepository.findAll()
                .stream()
                .filter(warranty -> warranty.getModel().equals(model))
                .collect(Collectors.toSet());
    }

    private Model getModel(Long id) {
        String pn = jpaNotebookRepository.findById(id).stream()
                .map(Notebook::getPn)
                .findFirst()
                .orElseThrow();
        String modelCode = pn.substring(0, 4);
        return modelRepository.findByPn(modelCode)
                .stream()
                .findAny()
                .orElseThrow();
    }
}
