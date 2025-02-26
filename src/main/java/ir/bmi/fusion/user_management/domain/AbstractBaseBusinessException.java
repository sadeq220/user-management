package ir.bmi.fusion.user_management.domain;

import lombok.Getter;
import org.springframework.http.HttpStatusCode;

@Getter
public abstract class AbstractBaseBusinessException extends RuntimeException {
    private final String code;
    private final Object[] args;
    private final HttpStatusCode httpStatusCode;

    public AbstractBaseBusinessException(String code, HttpStatusCode httpStatusCode, Object... args) {
        this.code = code;
        this.httpStatusCode = httpStatusCode;
        this.args = args;
    }
}
