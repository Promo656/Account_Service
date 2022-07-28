package account.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity(name = "Users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue
    private int id;
    @NotEmpty(message = "Name is empty")
    private String name;
    @NotEmpty
    private String lastname;
    @NotEmpty
    @Email(regexp = "\\w+@acme.com")
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
    private String email;
    @NotEmpty
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    @Pattern(regexp = "secret")
//    @Size(min = 12, message = "The password length must be at least 12 chars!")
    private String password;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String role;
}
