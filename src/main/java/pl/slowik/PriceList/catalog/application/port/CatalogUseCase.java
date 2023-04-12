package pl.slowik.PriceList.catalog.application.port;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import pl.slowik.PriceList.catalog.domain.Notebook;
import java.util.List;
import java.util.Optional;


public interface CatalogUseCase {
    Optional<Notebook> findById(Long id);
    List<Notebook> findAll();
    void deleteById(Long id);
    void updateNotebookPrice(UpdateNotebookPriceCommand command);


    @Value
    @Builder
    @AllArgsConstructor
    class UpdateNotebookPriceCommand {
        Long id;
        double bpPrice;
        double pbPricePln;
        double srpPrice;
    }
}
