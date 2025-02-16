package ir.bmi.fusion.user_management.domain.port.outbound;

import ir.bmi.fusion.user_management.domain.model.UserDomain;

public interface UserRepository {
    UserDomain saveUser(UserDomain userDomain);
}
