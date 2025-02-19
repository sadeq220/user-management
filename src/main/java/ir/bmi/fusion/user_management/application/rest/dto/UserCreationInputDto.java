package ir.bmi.fusion.user_management.application.rest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreationInputDto {
    private String firstName;
    private String lastName;
    private String nationalCode;
    private String personalCode;
    private String ssoId;
    private String username;
}
