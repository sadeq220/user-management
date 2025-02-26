package ir.bmi.fusion.user_management.domain.service.ex;

import ir.bmi.fusion.user_management.domain.AbstractBaseBusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public class PermissionNotFoundException extends AbstractBaseBusinessException {
    public PermissionNotFoundException(Object... args) {
        super("PERMISSION_NOT_FOUND", HttpStatus.BAD_REQUEST, args);
    }
}
