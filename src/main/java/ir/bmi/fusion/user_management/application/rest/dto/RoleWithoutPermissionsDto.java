package ir.bmi.fusion.user_management.application.rest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleWithoutPermissionsDto {
    private Long id;
    private String name;
    private String code;
}
