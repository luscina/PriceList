package pl.slowik.PriceList.catalog.application;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import pl.slowik.PriceList.catalog.application.port.CatalogUseCase;
import pl.slowik.PriceList.catalog.application.port.CatalogUseCase.UpdateNotebookPriceCommand;
import pl.slowik.PriceList.catalog.db.JpaNotebookRepository;
import pl.slowik.PriceList.catalog.domain.Notebook;

import java.math.BigDecimal;


@SpringBootTest
@AutoConfigureTestDatabase
class CatalogServiceTest {

    @Autowired
    CatalogUseCase catalog;

    @Autowired
    JpaNotebookRepository repository;

    @Test
    void updateNotebookPrice() {
        //given
        Notebook notebook = new Notebook();
        notebook.setId(1L);
        notebook.setBpPrice(new BigDecimal("10.99"));
        notebook.setBpPricePln(new BigDecimal("59.99"));
        notebook.setSrpPrice(new BigDecimal("89.99"));

        repository.save(notebook);

        UpdateNotebookPriceCommand command = new UpdateNotebookPriceCommand(1L, 12.99, 79.99, 119.00);

        //when

        catalog.updateNotebookPrice(command);
        notebook = repository.findById(1L).get();
        //then

        Assertions.assertEquals(notebook.getBpPrice(), new BigDecimal("12.99"));

    }
}