package account.Repositories;

import account.Entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PaymentsRepository extends JpaRepository<PaymentEntity, Long> {
    List<PaymentEntity> findPaymentsByEmployee(String employee);

    PaymentEntity findPaymentByPeriod(String period);

}
