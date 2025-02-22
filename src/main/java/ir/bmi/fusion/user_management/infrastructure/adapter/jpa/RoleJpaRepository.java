package ir.bmi.fusion.user_management.infrastructure.adapter.jpa;

import ir.bmi.fusion.user_management.domain.model.RoleDomain;
import ir.bmi.fusion.user_management.domain.port.outbound.RoleRepository;
import org.springframework.stereotype.Repository;

@Repository
public class RoleJpaRepository implements RoleRepository {
    private final RoleJpaRepositoryDAO roleJpaRepositoryDAO;
    private final PermissionJpaRepositoryDAO permissionJpaRepositoryDAO;

    public RoleJpaRepository(RoleJpaRepositoryDAO roleJpaRepositoryDAO, PermissionJpaRepositoryDAO permissionJpaRepositoryDAO) {
        this.roleJpaRepositoryDAO = roleJpaRepositoryDAO;
        this.permissionJpaRepositoryDAO = permissionJpaRepositoryDAO;
    }

    @Override
    public RoleDomain saveRole(RoleDomain roleDomain) {
        permissionJpaRepositoryDAO.saveAll(roleDomain.getPermissions());
        return roleJpaRepositoryDAO.save(roleDomain);
    }
}
