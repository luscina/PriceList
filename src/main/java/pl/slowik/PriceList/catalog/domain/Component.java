package pl.slowik.PriceList.catalog.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Setter
@Getter
@JsonIgnoreProperties({"compatibleModels"})
public class Component {
    @Id
    @GeneratedValue
    private Long id;
    private String pn;
    private String name;
    private String category;
    private String subCategory;
    private String EAN;
    private BigDecimal bpPrice;
    private BigDecimal bpPricePromo;
    @OneToMany(mappedBy = "component", cascade = CascadeType.ALL)
    private Set<ComponentModel> componentModelSet;

}
