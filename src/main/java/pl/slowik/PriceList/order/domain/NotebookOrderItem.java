package pl.slowik.PriceList.order.domain;

import pl.slowik.PriceList.catalog.domain.Notebook;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class NotebookOrderItem extends OrderItem{
    @ManyToOne
    @JoinColumn(name = "notebook_id")
    private Notebook notebook;
}
