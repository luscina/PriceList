package pl.slowik.PriceList.order.web;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class RestOrderNotebook {
    String pn;
    String productFamily;
    BigDecimal bpPrice;
}
