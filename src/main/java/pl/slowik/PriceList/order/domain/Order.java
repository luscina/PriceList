package pl.slowik.PriceList.order.domain;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "orders")
@EntityListeners(AuditingEntityListener.class)
public class Order {
    @Id
    @GeneratedValue
    private Long id;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    @Singular
    private Set<OrderItem> orderItems;
    @ManyToOne
    private Recipient recipient;
    public Order(Set<OrderItem> orderItems, Recipient recipient){
        this.orderItems = orderItems;
        this.recipient = recipient;
    }
}
