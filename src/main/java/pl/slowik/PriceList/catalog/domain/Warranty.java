package pl.slowik.PriceList.catalog.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "model")
public class Warranty extends BaseCatalogItem{
    private String pn;
    private String description;
    private String baseWarranty;
    @ManyToOne
    @JoinColumn(name = "model_id")
    private Model model;
}
