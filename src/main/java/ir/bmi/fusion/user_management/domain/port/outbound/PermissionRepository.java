package ir.bmi.fusion.user_management.domain.port.outbound;

import ir.bmi.fusion.user_management.domain.model.PermissionDomain;

import java.util.Optional;

public interface PermissionRepository {
    Optional<PermissionDomain> get(Long id);
}
