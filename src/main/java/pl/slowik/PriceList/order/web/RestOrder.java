package pl.slowik.PriceList.order.web;

import lombok.Value;

import java.util.Set;

@Value
public class RestOrder {
    Set<RestOrderItem> restItems;
    RestRecipient restRecipient;

}
