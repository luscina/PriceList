package pl.slowik.PriceList.catalog.application.port;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import pl.slowik.PriceList.catalog.domain.Component;
import pl.slowik.PriceList.catalog.domain.Notebook;
import pl.slowik.PriceList.catalog.domain.Warranty;

import java.util.List;
import java.util.Optional;
import java.util.Set;


public interface CatalogUseCase {
    Optional<Notebook> findById(Long id);
    List<Notebook> findAllNotebooks();
    void deleteNotebookById(Long id);
    void updateNotebookPrice(UpdateNotebookPriceCommand command);
    Set<Component> findCompileComponents(Long id);
    Set<Warranty> findCompileWarranties(Long id);
    Set<Component> findCompileMemory(Long notebookId);
    List<Warranty> findAllWarranties();
    List<String> findWarrantiesDescriptions();

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
