package ir.bmi.fusion.user_management.infrastructure.adapter.jpa;

import ir.bmi.fusion.user_management.domain.model.UserDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepositoryDAO extends JpaRepository<UserDomain,Long> {
}
