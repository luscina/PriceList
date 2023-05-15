package pl.slowik.PriceList.order.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Recipient {
    @Id
    @GeneratedValue
    Long id;
    private String email;
}
