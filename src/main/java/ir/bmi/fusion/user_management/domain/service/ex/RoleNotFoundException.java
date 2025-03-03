package ir.bmi.fusion.user_management.domain.service.ex;

import ir.bmi.fusion.user_management.domain.AbstractBaseBusinessException;
import org.springframework.http.HttpStatus;

public class RoleNotFoundException extends AbstractBaseBusinessException {
    public RoleNotFoundException(Object... args) {
        super("ROLE_NOT_FOUND", HttpStatus.BAD_REQUEST, args);
    }
}
