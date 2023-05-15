package pl.slowik.PriceList.order.domain;

import pl.slowik.PriceList.catalog.domain.Warranty;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class WarrantyOrderItem extends OrderItem{
    @ManyToOne
    @JoinColumn(name = "warrany_id")
    private Warranty warranty;
}
