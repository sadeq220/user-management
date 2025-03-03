package ir.bmi.fusion.user_management.domain.port.outbound;

import ir.bmi.fusion.user_management.domain.model.RoleDomain;

import java.util.Optional;

public interface RoleRepository {
    RoleDomain saveRole(RoleDomain roleDomain);
    Optional<RoleDomain> getRole(Long id);
}
