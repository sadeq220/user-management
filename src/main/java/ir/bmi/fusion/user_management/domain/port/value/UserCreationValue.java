package ir.bmi.fusion.user_management.domain.port.value;

import java.util.List;

public record UserCreationValue(String firstName,
                                String lastName,
                                String nationalCode,
                                String personalCode,
                                String ssoId,
                                String username,
                                List<Long> roleIds) {
}
