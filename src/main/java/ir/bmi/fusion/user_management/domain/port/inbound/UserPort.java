package ir.bmi.fusion.user_management.domain.port.inbound;

import ir.bmi.fusion.user_management.domain.port.value.UserValue;

public interface UserPort {
    UserValue addUser(UserValue userValue);
}
