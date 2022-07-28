package account.Servises;

import account.Entity.PaymentEntity;
import account.Entity.UserEntity;
import account.Repositories.PaymentsRepository;
import account.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PaymentsRepository paymentsRepository;

    public ResponseEntity<Object> payment(UserDetailImpl userDetails, String period) {
        if (period == null) {
            List<PaymentEntity> payments = paymentsRepository.findPaymentsByEmployee(userDetails.getEmail().toLowerCase());
            return new ResponseEntity<>(payments, HttpStatus.OK);
        }

        PaymentEntity payment = paymentsRepository.findPaymentByPeriod(period);
        return new ResponseEntity<>(payment, HttpStatus.OK);
    }
}
