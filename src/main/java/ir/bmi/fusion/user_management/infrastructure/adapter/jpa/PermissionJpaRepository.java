package ir.bmi.fusion.user_management.infrastructure.adapter.jpa;

import ir.bmi.fusion.user_management.domain.model.PermissionDomain;
import ir.bmi.fusion.user_management.domain.port.outbound.PermissionRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PermissionJpaRepository implements PermissionRepository {
    private final PermissionJpaRepositoryDAO permissionJpaRepositoryDAO;

    public PermissionJpaRepository(PermissionJpaRepositoryDAO permissionJpaRepositoryDAO) {
        this.permissionJpaRepositoryDAO = permissionJpaRepositoryDAO;
    }

    @Override
    public Optional<PermissionDomain> get(Long id) {
        return permissionJpaRepositoryDAO.getPermissionWithOneLevelParentJoining(id);
    }
}
