package pl.slowik.PriceList.catalog.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class ComponentFootnote {
    @Id
    @GeneratedValue
    private Long id;
    private String footnoteId;
    @Lob
    private String footnoteDesc;
}
