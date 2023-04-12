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
    @ManyToMany(mappedBy = "compatibleModels",
                fetch = FetchType.EAGER)
    private Set<Component> componentSet = new HashSet<>();

    public void addComponent(Component component){
        componentSet.add(component);
    }
}
