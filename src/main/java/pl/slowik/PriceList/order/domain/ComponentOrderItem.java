package pl.slowik.PriceList.order.domain;

import pl.slowik.PriceList.catalog.domain.Component;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class ComponentOrderItem extends OrderItem{
    @ManyToOne
    @JoinColumn(name = "component_id")
    private Component component;
}
