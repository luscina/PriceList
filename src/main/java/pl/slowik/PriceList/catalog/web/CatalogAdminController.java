package pl.slowik.PriceList.catalog.web;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.slowik.PriceList.catalog.application.CatalogInitializeService;

import javax.transaction.Transactional;

@RestController
@AllArgsConstructor
public class CatalogAdminController {
    private final CatalogInitializeService catalogInitializeService;
    @PostMapping("/admin")
    @Transactional
    public void initialize(){
        catalogInitializeService.initializeNotebooks();
        catalogInitializeService.initializeComponents();
        catalogInitializeService.initializeOCM();
    }
}
