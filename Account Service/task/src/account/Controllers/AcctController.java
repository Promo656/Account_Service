package account.Controllers;

import account.Entity.PaymentEntity;
import account.Servises.AcctService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/acct")
public class AcctController {
    @Autowired
    AcctService acctService;

    @PostMapping("/payments")
    public ResponseEntity<Map<String, String>> addPayments(@Valid @RequestBody List<PaymentEntity> payments) {
        return acctService.addPayments(payments);
    }

    @GetMapping("/payments")
    public ResponseEntity<List<PaymentEntity>> getAllPayments() {
        return acctService.getAllPayments();
    }
}
