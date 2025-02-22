package ir.bmi.fusion.user_management.domain.port.value;

import java.util.List;

public record RoleCreationValue(String name,
                                String code,
                                List<Long> permissionIds) {
}
