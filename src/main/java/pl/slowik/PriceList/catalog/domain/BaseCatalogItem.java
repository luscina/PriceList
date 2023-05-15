package pl.slowik.PriceList.catalog.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

@Setter
@Getter
@Entity
public abstract class BaseCatalogItem {
    @Id
    @GeneratedValue
    private Long id;
    private BigDecimal bpPrice;
}
