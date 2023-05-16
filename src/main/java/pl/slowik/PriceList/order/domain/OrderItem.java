package pl.slowik.PriceList.order.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.slowik.PriceList.catalog.domain.Notebook;

import javax.persistence.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "notebook_id")
    private Notebook notebook;
    private int quantity;
    public OrderItem(Notebook notebook, int quantity){
        this.notebook = notebook;
        this.quantity = quantity;
    }
}
