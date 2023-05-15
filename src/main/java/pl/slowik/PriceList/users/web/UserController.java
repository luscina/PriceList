package pl.slowik.PriceList.users.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.slowik.PriceList.users.application.port.UserRegisterUseCase;
import pl.slowik.PriceList.users.domain.UserEntity;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final UserRegisterUseCase registerUseCase;
    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void register(@RequestBody RegisterCommand command){
        registerUseCase.registerUser(
                command.username,
                command.password
        );
    }
    @GetMapping
    public List<UserEntity> findAllUsers(){
        return registerUseCase.findAllUsers();
    }

    @Data
    static class RegisterCommand {
        String username;
        String password;
    }
}
