package account.Entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.*;

@Entity(name = "Payments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentEntity {
    @NotEmpty
    @Email
    private String employee;
    @Id
    @NotEmpty
    @Pattern(regexp = "(0[1-9]|1[0-2])-[1-2][0-1]\\d\\d")
    private String period;
    @NotNull
    private int salary;
}
