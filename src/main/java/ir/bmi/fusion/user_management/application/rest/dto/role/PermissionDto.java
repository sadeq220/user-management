package ir.bmi.fusion.user_management.application.rest.dto.role;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.bmi.fusion.user_management.domain.port.value.PermissionValue;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PermissionDto {
    private Long id;
    private String name;
    private String code;
    private String value;
    private Long parentId;
}
