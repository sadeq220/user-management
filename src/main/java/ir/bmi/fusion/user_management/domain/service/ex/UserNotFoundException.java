package ir.bmi.fusion.user_management.domain.service.ex;

import ir.bmi.fusion.user_management.domain.AbstractBaseBusinessException;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends AbstractBaseBusinessException {
    public UserNotFoundException(Object... args) {
        super("USER_NOT_FOUND", HttpStatus.BAD_REQUEST, args);
    }
}
