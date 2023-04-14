package pl.slowik.PriceList.catalog.web;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.slowik.PriceList.catalog.application.CatalogInitializeService;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class CatalogAdminController {
    private final CatalogInitializeService catalogInitializeService;
    @PostMapping("/components")
    @Transactional
    public void initializeComponents(){
        catalogInitializeService.initializeComponents();
    }
    @PostMapping("/models")
    @Transactional
    public void initializeModels(){
        catalogInitializeService.initializeModels();
    }

    @PostMapping("/notebooks")
    private void initializeNotebooks(){
        catalogInitializeService.initializeNotebooks();
    }

}
