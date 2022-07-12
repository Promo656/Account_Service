package account.Servises;

import account.Repositories.UserRepository;
import account.Entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServices {
    @Autowired
    UserRepository userRepository;
    public Map<String, String> getAll() {
        return Map.of("1","w");
    }
}
