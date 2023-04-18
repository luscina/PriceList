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
public class Component {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String pn;
    private String name;
    private String category;
    private String subCategory;
    private String EAN;
    private BigDecimal bpPrice;
    private BigDecimal bpPricePromo;
    @OneToMany(mappedBy = "model")
    private Set<ComponentModel> componentModels = new HashSet<>();
}
