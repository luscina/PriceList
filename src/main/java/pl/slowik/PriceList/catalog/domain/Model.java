package pl.slowik.PriceList.catalog.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @OneToMany(mappedBy = "model", cascade = CascadeType.ALL)
    private Set<ComponentModel> componentModelSet = new HashSet<>();

    public void addComponentModel(ComponentModel componentModel){
        componentModelSet.add(componentModel);
    }
}
