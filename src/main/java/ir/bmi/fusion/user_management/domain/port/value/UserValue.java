package ir.bmi.fusion.user_management.domain.port.value;

import java.time.Instant;

public record UserValue(Long id,
                        String firstName,
                        String lastName,
                        String nationalCode,
                        String personalCode,
                        String ssoId,
                        String username,
                        Instant createDate) {
}
