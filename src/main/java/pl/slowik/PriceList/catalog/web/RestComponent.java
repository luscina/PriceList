package pl.slowik.PriceList.catalog.web;

import lombok.Value;
import java.math.BigDecimal;

@Value
public class RestComponent {
    Long id;
    String pn;
    String name;
    String category;
    String subCategory;
    String EAN;
    BigDecimal bpPrice;
    BigDecimal bpPricePromo;
}
