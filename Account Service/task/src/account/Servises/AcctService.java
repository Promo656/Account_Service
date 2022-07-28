package account.Servises;

import account.Entity.PaymentEntity;
import account.Repositories.PaymentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Service
public class AcctService {
    @Autowired
    PaymentsRepository paymentsRepository;

    public ResponseEntity<Map<String, String>> addPayments(List<PaymentEntity> payments) {
        payments.forEach(payment->payment.setEmployee(payment.getEmployee().toLowerCase()));
        paymentsRepository.saveAll(payments);
        return new ResponseEntity<>(Map.of("status", "Added successfully!"), HttpStatus.OK);
    }

    public ResponseEntity<List<PaymentEntity>> getAllPayments() {
        List<PaymentEntity> payments = paymentsRepository.findAll();
        return new ResponseEntity<>(payments, HttpStatus.OK);
    }
}
