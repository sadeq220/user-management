package ir.bmi.fusion.user_management.infrastructure.adapter.event.stream.dto;

import java.time.Instant;
import java.util.List;

public record UserEvent(Long id,
                        String firstName,
                        String lastName,
                        String nationalCode,
                        String personalCode,
                        String ssoId,
                        String username,
                        Instant createDate,
                        List<PermissionEvent> permissions,
                        List<RoleEvent> roles) {
}
