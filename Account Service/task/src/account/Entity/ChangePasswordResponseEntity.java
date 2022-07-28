package account.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordResponseEntity {
    public ChangePasswordResponseEntity(String email) {
        this.email = email;
    }

    private String email;
    private String status = "The password has been updated successfully";
}
