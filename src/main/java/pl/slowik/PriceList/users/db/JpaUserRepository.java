package pl.slowik.PriceList.users.db;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.slowik.PriceList.users.domain.UserEntity;

import java.util.Optional;

public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsernameIgnoreCase(String username);
}
