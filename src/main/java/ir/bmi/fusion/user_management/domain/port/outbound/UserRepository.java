package ir.bmi.fusion.user_management.domain.port.outbound;

import ir.bmi.fusion.user_management.domain.model.UserDomain;

import java.util.Optional;

public interface UserRepository {
    UserDomain saveUser(UserDomain userDomain);
    Optional<UserDomain> getUser(Long id);
}
