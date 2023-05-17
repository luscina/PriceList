package pl.slowik.PriceList.order.web;

import lombok.Value;
@Value
public class RestOrderItem {
    RestOrderNotebook restOrderNotebook;
    int quantity;
}
