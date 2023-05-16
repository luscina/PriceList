package pl.slowik.PriceList.catalog.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString(exclude = {"warranties", "notebooks"})
public class Model {
    @Id
    @GeneratedValue
    private Long id;
    private String pn;
    @OneToMany(mappedBy = "component", cascade = CascadeType.ALL)
    private Set<ComponentModel> componentModels = new HashSet<>();

    public void addComponentModel(ComponentModel componentModel){
        componentModels.add(componentModel);
    }
    @OneToMany(mappedBy = "model")
    @JsonIgnore
    private Set<Notebook> notebooks;
    @OneToMany(mappedBy = "model")
    private Set<Warranty> warranties;
}
