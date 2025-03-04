package ir.bmi.fusion.user_management.infrastructure.adapter.jpa;

import ir.bmi.fusion.user_management.domain.model.RoleDomain;
import ir.bmi.fusion.user_management.domain.port.outbound.RoleRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<RoleDomain> getRole(Long id) {
        return roleJpaRepositoryDAO.findById(id);
    }

    @Override
    public List<RoleDomain> getRoles(List<Long> roleIds) {
        return roleJpaRepositoryDAO.getRoles(roleIds);
    }

}
