package ir.bmi.fusion.user_management.application.rest.dto.role;

import ir.bmi.fusion.user_management.domain.port.value.PermissionCreationValue;
import ir.bmi.fusion.user_management.domain.port.value.PermissionValue;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PermissionDtoMapper {
    PermissionCreationValue toValue(PermissionCreationDto permissionCreationDto);
    @Mapping(target = "parentId",source="parentPermission.id")
    PermissionDto toDto(PermissionValue permissionValue);
}
