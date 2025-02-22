package ir.bmi.fusion.user_management.application.rest.dto.role;

import ir.bmi.fusion.user_management.domain.port.value.RoleCreationValue;
import ir.bmi.fusion.user_management.domain.port.value.RoleValue;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleDtoMapper {
    RoleCreationValue toValue(RoleCreationDto roleCreationDto);
    RoleDto toDto(RoleValue roleValue);
}
