package account.Exceptions;

import org.springframework.http.HttpStatus;

public class FieldValidException extends RuntimeException {
    public FieldValidException(String message) {
        super(message);
    }
}
