package ir.bmi.fusion.user_management.application.rest.dto.role;

import ir.bmi.fusion.user_management.domain.port.value.PermissionCreationValue;
import ir.bmi.fusion.user_management.domain.port.value.PermissionValue;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionDtoMapper {
    PermissionCreationValue toValue(PermissionCreationDto permissionCreationDto);
    PermissionDto toDto(PermissionValue permissionValue);
}
