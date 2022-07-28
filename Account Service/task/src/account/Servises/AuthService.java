package account.Servises;

import account.Entity.*;

import account.Exceptions.FieldValidException;
import account.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;

    List<String> breachedPasswords = new ArrayList<>() {{
        add("PasswordForJanuary");
        add("PasswordForFebruary");
        add("PasswordForMarch");
        add("PasswordForApril");
        add("PasswordForMay");
        add("PasswordForJune");
        add("PasswordForJuly");
        add("PasswordForAugust");
        add("PasswordForSeptember");
        add("PasswordForOctober");
        add("PasswordForNovember");
        add("PasswordForDecember");
    }};

    boolean checkBreachedPasswords(String password) {
        return breachedPasswords.contains(password);
    }

    public ResponseEntity<UserEntity> signup(UserEntity userEntity) {
        if (checkBreachedPasswords(userEntity.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The password is in the hacker's database!");
        }

        if (userRepository.findUserByEmail(userEntity.getEmail().toLowerCase()).isEmpty()) {
            userEntity.setEmail(userEntity.getEmail().toLowerCase());
            userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
            userEntity.setRole(Roles.USER.name());
            userRepository.save(userEntity);
            return new ResponseEntity<>(userEntity, HttpStatus.OK);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User exist!");
    }

    public ResponseEntity<ChangePasswordResponseEntity> changePassword(ChangePasswordRequestEntity newPass, UserDetailImpl userDetail) {
        if (newPass.getNew_password().length() < 12) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password length must be 12 chars minimum!");
        }
        if (passwordEncoder.matches(newPass.getNew_password(), userDetail.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The passwords must be different!");
        }

        if (checkBreachedPasswords(newPass.getNew_password())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The password is in the hacker's database!");
        }

        userRepository.findUserByEmail(userDetail.getEmail()).ifPresent(user -> {
            user.setPassword(passwordEncoder.encode(newPass.getNew_password()));
            userRepository.save(user);
        });

        return new ResponseEntity<>(new ChangePasswordResponseEntity(userDetail.getEmail()), HttpStatus.OK);
    }
}
