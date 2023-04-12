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
    @GeneratedValue
    private Long id;
    private String pn;
    private String name;
    private String category;
    private String subCategory;
    private String EAN;
    private BigDecimal bpPrice;
    private BigDecimal bpPricePromo;
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
            }, fetch = FetchType.EAGER)
    @JoinTable
    private Set<Model> compatibleModels = new HashSet<>();

    public void addModel(Model model){
        compatibleModels.add(model);
    }
}
