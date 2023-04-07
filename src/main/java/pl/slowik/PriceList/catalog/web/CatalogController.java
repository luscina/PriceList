package pl.slowik.PriceList.catalog.web;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.slowik.PriceList.catalog.application.port.CatalogUseCase;
import pl.slowik.PriceList.catalog.domain.Notebook;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/notebook")
@RestController
@AllArgsConstructor
public class CatalogController {

    private final CatalogUseCase catalogUseCase;


    @GetMapping("/{id}")
    private ResponseEntity<RestNotebook> findById(@PathVariable Long id) {
        return catalogUseCase.findById(id).stream().map(this::toRestNotebook).findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());

    }
    @GetMapping
    private List<RestNotebook> getAll(){
        return catalogUseCase.findAll()
                .stream()
                .map(this::toRestNotebook)
                .collect(Collectors.toList());
    }

    private RestNotebook toRestNotebook(Notebook notebook){
        return new RestNotebook(
                notebook.getId(),
                notebook.getPn(),
                notebook.getEanCode(),
                notebook.getProductFamily(),
                notebook.getProductSeries(),
                notebook.getStatus(),
                notebook.getBpPrice(),
                notebook.getBpPricePln(),
                notebook.getSrpPrice(),
                notebook.getBase(),
                notebook.getColor(),
                notebook.getPanel(),
                notebook.getCup(),
                notebook.getMemory(),
                notebook.getSsd(),
                notebook.getHdd(),
                notebook.getGraphics(),
                notebook.getOdd(),
                notebook.getWlan(),
                notebook.getWwan(),
                notebook.getBacklit(),
                notebook.getFrp(),
                notebook.getCamera(),
                notebook.getKeyboard(),
                notebook.getCardReader(),
                notebook.getOs(),
                notebook.getWarranty(),
                notebook.getBattery(),
                notebook.getAdapter()
                );
    }
}
