package ir.bmi.fusion.user_management.infrastructure.adapter.jpa;

import ir.bmi.fusion.user_management.domain.model.UserDomain;
import ir.bmi.fusion.user_management.domain.port.outbound.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserJpaRepository implements UserRepository {
    private final UserJpaRepositoryDAO userJpaRepositoryDAO;

    public UserJpaRepository(UserJpaRepositoryDAO userJpaRepositoryDAO) {
        this.userJpaRepositoryDAO = userJpaRepositoryDAO;
    }

    @Override
    public UserDomain saveUser(UserDomain userDomain) {
        return userJpaRepositoryDAO.save(userDomain);
    }

    @Override
    public Optional<UserDomain> getUser(Long id) {
        return userJpaRepositoryDAO.getUser(id);
    }
}
