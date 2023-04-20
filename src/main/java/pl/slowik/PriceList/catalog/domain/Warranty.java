package pl.slowik.PriceList.catalog.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Warranty {
    @Id
    @GeneratedValue
    private Long id;
    private String pn;
    private String description;
    private String baseWarranty;
    @ManyToOne
    @JoinColumn(name = "model_id", nullable = false)
    private Model model;
    private BigDecimal bpPrice;

}
