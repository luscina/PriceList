package pl.slowik.PriceList.catalog.web;
import lombok.AllArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.slowik.PriceList.catalog.application.CatalogInitializeService;
import javax.transaction.Transactional;

@RestController
@Secured("ROLE_ADMIN")
@RequestMapping("/admin")
@AllArgsConstructor
public class CatalogAdminController {
    private final CatalogInitializeService catalogInitializeService;
    @PostMapping("/components")
    @Transactional
    public void initializeComponents(){
        catalogInitializeService.initializeComponents();
    }
    @PostMapping("/notebooks")
    @Transactional
    public void initializeNotebooks(){
        catalogInitializeService.initializeNotebooks();
    }
    @PostMapping("/models")
    @Transactional
    public void initializeModels(){
        catalogInitializeService.initializeModels();
    }
    @PostMapping("/warranties")
    @Transactional
    public void initializeWarranties(){
        catalogInitializeService.initializeWarranties();
    }
    @PostMapping("/footnotes")
    public void initializeFootnotes() {
        catalogInitializeService.initializeFootnotes();
    }
    @PostMapping("/all")
    @Transactional
    public void initializeAll() {
        catalogInitializeService.initializeComponents();
        catalogInitializeService.initializeModels();
        catalogInitializeService.initializeNotebooks();
        catalogInitializeService.initializeWarranties();
    }
}
