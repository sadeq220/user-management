package ir.bmi.fusion.user_management.domain.port.outbound;

import ir.bmi.fusion.user_management.domain.model.RoleDomain;

public interface RoleRepository {
    RoleDomain saveRole(RoleDomain roleDomain);
}
