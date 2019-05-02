package art.dev.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserConfirmPasswordException extends RuntimeException {

    public UserConfirmPasswordException(String message) {
        super(message, null, false, false);
    }
}