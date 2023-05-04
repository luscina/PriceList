package pl.slowik.PriceList.catalog.web;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class RestWarranty {
    String pn;
    String description;
    String baseWarranty;
    BigDecimal bpPrice;
}
