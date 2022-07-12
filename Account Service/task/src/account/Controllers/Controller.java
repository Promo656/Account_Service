package account.Controllers;

import account.Entity.UserEntity;
import account.Servises.AuthService;
import account.Servises.UserServices;
import account.UserDetailImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class Controller {
    @Autowired
    UserServices userServices;
    @Autowired
    AuthService authService;

    @PostMapping("/actuator/shutdown")
    public void shutdown() {
    }

    @PostMapping("/api/auth/signup")
    public ResponseEntity<UserEntity> signup(@Valid @RequestBody UserEntity userEntity) {
        return authService.signup(userEntity);
    }

    @GetMapping("/api/empl/payment")
    public ResponseEntity<UserEntity> payment(@AuthenticationPrincipal UserDetailImpl userDetails) {
        return authService.payment(userDetails);
    }
}
