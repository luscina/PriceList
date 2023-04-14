package pl.slowik.PriceList.catalog.domain;

import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "COMPONENT_MODEL")
@NoArgsConstructor
@Setter
public class ComponentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "component_id")
    private Component component;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "model_id")
    private Model model;
    private String comment;

    public ComponentModel(Component component, Model model) {
        this.component = component;
        this.model = model;
    }
}
