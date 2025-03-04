package ir.bmi.fusion.user_management.domain.port.value;

import java.time.Instant;
import java.util.List;

public record UserValue(Long id,
                        String firstName,
                        String lastName,
                        String nationalCode,
                        String personalCode,
                        String ssoId,
                        String username,
                        Instant createDate,
                        List<PermissionValue> permissions,
                        List<RoleValue> roles) {
}
