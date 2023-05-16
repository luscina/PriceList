package pl.slowik.PriceList.catalog.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "model")
public class Warranty {
    @Id
    @GeneratedValue
    private Long id;
    private BigDecimal bpPrice;
    private String pn;
    private String description;
    private String baseWarranty;
    @ManyToOne
    @JoinColumn(name = "model_id")
    private Model model;
}
