package pl.slowik.PriceList.users.domain;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue
    Long id;
    private String username;
    private String password;
    public UserEntity(String username, String password){
        this.username = username;
        this.password = password;
    }
}
