package ir.bmi.fusion.user_management.domain.port.value;

import org.springframework.lang.Nullable;

public record PermissionCreationValue(String name,
                                      String code,
                                      String value,
                                      String type,
                                      @Nullable Long parentId) {
}
