package ir.bmi.fusion.user_management.domain.port.inbound;

import ir.bmi.fusion.user_management.domain.port.value.PermissionCreationValue;
import ir.bmi.fusion.user_management.domain.port.value.PermissionValue;

public interface PermissionPort {
    PermissionValue addPermission(PermissionCreationValue permissionCreationValue);
}
