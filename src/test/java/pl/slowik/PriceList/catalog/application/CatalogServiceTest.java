package pl.slowik.PriceList.catalog.application;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import pl.slowik.PriceList.catalog.application.port.CatalogUseCase;
import pl.slowik.PriceList.catalog.application.port.CatalogUseCase.UpdateNotebookPriceCommand;
import pl.slowik.PriceList.catalog.db.*;
import pl.slowik.PriceList.catalog.domain.*;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@SpringBootTest
@AutoConfigureTestDatabase
class CatalogServiceTest {
    @Autowired
    CatalogUseCase catalog;
    @Autowired
    JpaNotebookRepository notebookRepository;
    @Autowired
    JpaModelRepository modelRepository;
    @Autowired
    JpaComponentModelRepository componentModelRepository;
    @Autowired
    JpaComponentRepository componentRepository;
    @Autowired
    JpaWarrantyRepository warrantyRepository;
    @Test
    void findAll(){
        //given
        Notebook notebook1 = new Notebook();
        notebook1.setId(1L);
        Notebook notebook2 = new Notebook();
        notebook2.setId(2L);
        notebookRepository.save(notebook1);
        notebookRepository.save(notebook2);

        //when
        List<Notebook> all = catalog.findAllNotebooks();

        //then
        Assertions.assertEquals(2, all.size());
    }
    @Test
    @Transactional
    void findById() {
        //given
        Notebook notebook = new Notebook();
        notebook.setId(1L);
        notebookRepository.save(notebook);
        //when

        Optional<Notebook> byId = catalog.findById(1L);
        //then

        Assertions.assertEquals(notebook.getId(), byId.orElseThrow().getId());
    }
    @Test
    @Transactional
    void updateNotebookPrice() {
        //given
        Notebook notebook = new Notebook();
        notebook.setId(1L);
        notebook.setBpPrice(new BigDecimal("10.99"));
        notebook.setBpPricePln(new BigDecimal("59.99"));
        notebook.setSrpPrice(new BigDecimal("89.99"));
        notebookRepository.save(notebook);

        //when
        UpdateNotebookPriceCommand command = new UpdateNotebookPriceCommand(1L, 12.99, 79.99, 119.00);
        catalog.updateNotebookPrice(command);
        notebook = notebookRepository.findById(1L).orElseThrow();

        //then
        Assertions.assertEquals(notebook.getBpPrice(), new BigDecimal("12.99"));
    }
    @Test
    @Transactional
    void deleteById(){
        //given
        Notebook notebook1 = new Notebook();
        notebook1.setId(1L);
        Notebook notebook2 = new Notebook();
        notebook2.setId(2L);
        notebookRepository.save(notebook1);
        notebookRepository.save(notebook2);

        //when
        catalog.deleteNotebookById(1L);
        List<Notebook> all = catalog.findAllNotebooks();
        //then
        Assertions.assertEquals(1, all.size());
    }
    @Test
    @Transactional
    void findCompileComponents(){
        //given
        Notebook notebook1 = new Notebook();
        notebook1.setId(1L);
        notebook1.setPn("21ABCDEF");
        notebookRepository.save(notebook1);
        Model model = new Model();
        model.setPn("21AB");
        modelRepository.save(model);
        Component component = new Component();
        component.setId(1L);
        componentRepository.save(component);
        ComponentModel componentModel = new ComponentModel();
        componentModel.setComponent(component);
        componentModel.setModel(model);
        componentModelRepository.save(componentModel);

        //when
        Set<Component> compileComponents = catalog.findCompileComponents(1L);

        //then
        Assertions.assertEquals(1, compileComponents.size());
    }
    @Test
    @Transactional
    void findCompileMemory(){
        //given
        Notebook notebook1 = new Notebook();
        notebook1.setId(1L);
        notebook1.setPn("21ABCDEF");
        notebookRepository.save(notebook1);
        Model model = new Model();
        model.setPn("21AB");
        modelRepository.save(model);
        Component component1 = new Component();
        component1.setId(1L);
        component1.setSubCategory("Memory");
        Component component2 = new Component();
        component2.setId(2L);
        component2.setSubCategory("CPU");
        componentRepository.save(component1);
        componentRepository.save(component2);
        ComponentModel componentModel1 = new ComponentModel();
        componentModel1.setComponent(component1);
        componentModel1.setModel(model);
        ComponentModel componentModel2 = new ComponentModel();
        componentModel2.setModel(model);
        componentModel2.setComponent(component2);
        componentModelRepository.save(componentModel1);
        componentModelRepository.save(componentModel2);

        //when
        Set<Component> compileComponents = catalog.findCompileMemory(1L);

        //then
        Assertions.assertEquals(1, compileComponents.size());
    }
    @Test
    @Transactional
    void findAllWarranties(){
        //given
        Warranty warranty1 = new Warranty();
        Warranty warranty2 = new Warranty();
        warrantyRepository.save(warranty1);
        warrantyRepository.save(warranty2);

        //when
        List<Warranty> allWarranties = catalog.findAllWarranties();

        //then
        Assertions.assertEquals(2, allWarranties.size());
    }
//    @Test
//    @Transactional
//    void findCompileWarranties(){
//        //given
//        Warranty warranty1 = new Warranty();
//        Warranty warranty2 = new Warranty();
//        warrantyRepository.save(warranty1);
//        warrantyRepository.save(warranty2);
//        Notebook notebook = new Notebook();
//        notebook.setId(1L);
//        notebook.setPn("21AB");
//        Model model = new Model();
//        model.setId(1L);
//        model.setPn("21AB");
//        model.setWarranties(Set.of(warranty1, warranty2));
//        model.setNotebooks(Set.of(notebook));
//        notebook.setModel(model);
//        modelRepository.save(model);
//        notebookRepository.save(notebook);
//
//
//        //when
//        Set<Warranty> compileWarranties = catalog.findCompileWarranties(1L);
//
//        //then
//        Assertions.assertEquals(2, compileWarranties.size());
//
//    }
}
