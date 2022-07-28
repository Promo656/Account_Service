package account.Controllers;

import account.Entity.ChangePasswordRequestEntity;
import account.Entity.ChangePasswordResponseEntity;
import account.Entity.UserEntity;
import account.Servises.AuthService;
import account.Servises.UserDetailImpl;
import account.Servises.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@Validated
public class Controller {
    @Autowired
    UserServices userServices;
    @Autowired
    AuthService authService;

//    @PostMapping("/actuator/shutdown")
//    public void shutdown() {
//    }

    @PostMapping("/signup")
    public ResponseEntity<UserEntity> signup(@Valid @RequestBody UserEntity userEntity) {
        return authService.signup(userEntity);
    }

    @PostMapping("/changepass")
    public ResponseEntity<ChangePasswordResponseEntity> changePassword(@Valid @RequestBody ChangePasswordRequestEntity newPass, @AuthenticationPrincipal UserDetailImpl userDetail) {
        return authService.changePassword(newPass, userDetail);
    }
}
