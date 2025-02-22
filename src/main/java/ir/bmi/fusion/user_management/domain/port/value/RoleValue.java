package ir.bmi.fusion.user_management.domain.port.value;

import java.util.List;

public record RoleValue(Long id,
                        String name,
                        String code,
                        List<PermissionValue> permissions) {
}
