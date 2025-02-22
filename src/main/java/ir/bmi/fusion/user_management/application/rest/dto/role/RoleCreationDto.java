package ir.bmi.fusion.user_management.application.rest.dto.role;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RoleCreationDto {
    private String name;
    private String code;
    private List<Long> permissionIds;
}
