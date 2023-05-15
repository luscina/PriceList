package pl.slowik.PriceList.users.application.port;

import pl.slowik.PriceList.users.domain.UserEntity;

import java.util.List;

public interface UserRegisterUseCase {
    void registerUser(String username, String password);
    List<UserEntity> findAllUsers();
}
