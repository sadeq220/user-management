package ir.bmi.fusion.user_management.infrastructure.adapter.jpa;

import ir.bmi.fusion.user_management.domain.model.RoleDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleJpaRepositoryDAO extends JpaRepository<RoleDomain,Long> {
    @Query("SELECT role FROM RoleDomain role " +
            "JOIN FETCH role.permissions permissions " +
            "JOIN FETCH permissions.permissionDomain permission " +
            "JOIN FETCH permission.parentPermission parentPermission " +
            "WHERE role.id IN :roleIds")
    List<RoleDomain> getRoles(@Param("roleIds") List<Long> roleIds);
}
