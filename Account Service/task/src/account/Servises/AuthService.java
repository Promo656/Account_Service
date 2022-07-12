package account.Servises;

import account.Entity.Roles;
import account.Entity.UserEntity;

import account.Repositories.UserRepository;
import account.UserDetailImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;

    public ResponseEntity<UserEntity> signup(UserEntity userEntity) {
        if (userRepository.findUserByEmail(userEntity.getEmail().toLowerCase()).isEmpty()) {
            userEntity.setEmail(userEntity.getEmail().toLowerCase());
            userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
            userEntity.setRole(Roles.USER.name());
            userRepository.save(userEntity);
            return new ResponseEntity<>(userEntity, HttpStatus.OK);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User exist!");
    }

    public ResponseEntity<UserEntity> payment(UserDetailImpl userDetails) {
        return userRepository.findUserByEmail(userDetails.getEmail())
                .map(el -> new ResponseEntity<>(el, HttpStatus.OK)).orElseThrow(() -> {
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
                });
    }
}
