package pl.slowik.PriceList.catalog.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Lob;

@Entity
@Getter
@Setter
public class ComponentFootnote extends BaseCatalogItem {
    private String footnoteId;
    @Lob
    private String footnoteDesc;
}
