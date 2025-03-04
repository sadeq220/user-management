package ir.bmi.fusion.user_management.domain.port.inbound;

import ir.bmi.fusion.user_management.domain.port.value.UserCreationValue;
import ir.bmi.fusion.user_management.domain.port.value.UserValue;

public interface UserPort {
    UserValue addUser(UserCreationValue userCreationValue);
    UserValue getUser(Long id);
}
