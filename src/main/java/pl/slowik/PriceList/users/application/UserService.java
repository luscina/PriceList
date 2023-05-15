package pl.slowik.PriceList.users.application;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.slowik.PriceList.users.application.port.UserRegisterUseCase;
import pl.slowik.PriceList.users.db.JpaUserRepository;
import pl.slowik.PriceList.users.domain.UserEntity;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements UserRegisterUseCase {
    private final JpaUserRepository userRepository;
    private final PasswordEncoder encoder;
    @Override
    public void registerUser(String username, String password) {
        if(userRepository.findByUsernameIgnoreCase(username).isEmpty()){
            UserEntity user = new UserEntity(username, encoder.encode(password));
            userRepository.save(user);
        }
    }

    @Override
    public List<UserEntity> findAllUsers() {
        return userRepository.findAll();
    }
}
