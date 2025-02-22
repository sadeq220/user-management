package ir.bmi.fusion.user_management.domain.port.value;

import org.springframework.lang.Nullable;

public record PermissionValue(String name,
                              String code,
                              String value,
                              @Nullable PermissionValue permissionValue) {
}
