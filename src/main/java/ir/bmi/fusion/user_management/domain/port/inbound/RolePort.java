package ir.bmi.fusion.user_management.domain.port.inbound;

import ir.bmi.fusion.user_management.domain.port.value.RoleCreationValue;
import ir.bmi.fusion.user_management.domain.port.value.RoleValue;

public interface RolePort {
    RoleValue addRole(RoleCreationValue roleCreationValue);
}
