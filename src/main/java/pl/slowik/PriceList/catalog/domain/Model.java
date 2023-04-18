package pl.slowik.PriceList.catalog.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
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
}
