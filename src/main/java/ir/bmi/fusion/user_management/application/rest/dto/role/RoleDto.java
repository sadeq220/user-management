package ir.bmi.fusion.user_management.application.rest.dto.role;

import ir.bmi.fusion.user_management.application.rest.dto.role.PermissionDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class RoleDto {
    private Long id;
    private String name;
    private String code;
    private List<PermissionDto> permissions;
}
