package account.Exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
    private String message;
    private int status = 400;
    private String error = "Bad Request";
    private String path = "/api/auth/changepass";
    private String timestamp = "2022-07-14T13:11:04.697+00:00";
}