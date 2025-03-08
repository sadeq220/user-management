package ir.bmi.fusion.user_management.infrastructure.adapter.jpa;

import ir.bmi.fusion.user_management.domain.model.UserDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserJpaRepositoryDAO extends JpaRepository<UserDomain,Long> {
    @Query("SELECT user FROM UserDomain user " +
            "LEFT JOIN FETCH user.userRoles roles " +
            "LEFT JOIN FETCH roles.roleDomain role " +
            "WHERE user.id=:id")
    Optional<UserDomain> getUser(@Param("id") Long id);
}
