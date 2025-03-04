package ir.bmi.fusion.user_management.application.rest.dto;

import ir.bmi.fusion.user_management.application.rest.dto.role.PermissionDto;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

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
    private List<PermissionDto> permissions;
    private List<RoleWithoutPermissionsDto> roles;
}
