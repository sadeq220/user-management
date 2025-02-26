package ir.bmi.fusion.user_management.domain.service.ex;

import ir.bmi.fusion.user_management.domain.AbstractBaseBusinessException;
import org.springframework.http.HttpStatus;

public class PermissionTypeNotValidException extends AbstractBaseBusinessException {
    public PermissionTypeNotValidException(Object... args) {
        super("PERMISSION_TYPE_NOT_VALID", HttpStatus.BAD_REQUEST,args);
    }
}
