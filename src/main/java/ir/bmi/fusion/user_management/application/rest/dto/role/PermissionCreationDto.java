package ir.bmi.fusion.user_management.application.rest.dto.role;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

@Setter
@Getter
public class PermissionCreationDto {
    private String name;
    private String code;
    private String value;
    private String type;
    @Nullable
    private Long parentId;
}
