package ir.bmi.fusion.user_management.domain.port.outbound;

import ir.bmi.fusion.user_management.domain.port.value.UserValue;

public interface UserEventPublisher {
    void userCreated(UserValue userValue);
}
