package ir.bmi.fusion.user_management.application.rest.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Setter
@Getter
public class UserInfoDto {
    Long id;
    String firstName;
    String lastName;
    String nationalCode;
    String personalCode;
    String ssoId;
    String username;
    Instant createDate;
}
