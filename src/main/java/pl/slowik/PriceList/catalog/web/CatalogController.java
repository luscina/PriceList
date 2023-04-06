package pl.slowik.PriceList.catalog.web;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import pl.slowik.PriceList.catalog.application.CatalogInitializeService;
import pl.slowik.PriceList.catalog.application.port.CatalogUseCase;
import pl.slowik.PriceList.catalog.domain.Notebook;

import javax.transaction.Transactional;
import java.util.List;

@RequestMapping("/notebook")
@RestController
@AllArgsConstructor
public class CatalogController {

    private final CatalogUseCase catalogUseCase;


    @GetMapping("/{id}")
    private ResponseEntity<?> findById(@PathVariable Long id){
        return catalogUseCase.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping
    private List<Notebook> getAll(){
        return catalogUseCase.findAll();
    }
}
