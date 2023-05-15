package pl.slowik.PriceList.catalog.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
public class Component extends BaseCatalogItem {
    private String pn;
    private String name;
    private String category;
    private String subCategory;
    private String EAN;
    private BigDecimal bpPricePromo;
    @OneToMany(mappedBy = "model")
    private Set<ComponentModel> componentModels = new HashSet<>();
}
