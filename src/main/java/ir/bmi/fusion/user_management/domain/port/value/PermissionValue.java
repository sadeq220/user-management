package ir.bmi.fusion.user_management.domain.port.value;

import org.springframework.lang.Nullable;

public record PermissionValue(Long id,
                              String name,
                              String code,
                              String value,
                              @Nullable PermissionValue parentPermission) {
}
