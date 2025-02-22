package ir.bmi.fusion.user_management.infrastructure.adapter.jpa;

import ir.bmi.fusion.user_management.domain.model.PermissionDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PermissionJpaRepositoryDAO extends JpaRepository<PermissionDomain,Long> {
    @Query("SELECT per FROM permission per JOIN fetch per.parentPermission WHERE per.id = :id")
    Optional<PermissionDomain> getPermissionWithOneLevelParentJoining(@Param("id")Long id);
}
