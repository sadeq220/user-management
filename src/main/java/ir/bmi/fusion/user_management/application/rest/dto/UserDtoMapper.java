package ir.bmi.fusion.user_management.application.rest.dto;

import ir.bmi.fusion.user_management.application.rest.dto.role.PermissionDtoMapper;
import ir.bmi.fusion.user_management.domain.port.value.RoleValue;
import ir.bmi.fusion.user_management.domain.port.value.UserCreationValue;
import ir.bmi.fusion.user_management.domain.port.value.UserValue;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",uses = {PermissionDtoMapper.class})
public interface UserDtoMapper {
    UserInfoDto toDto(UserValue userValue);
    UserCreationValue toValue(UserCreationInputDto userCreationInputDto);

    RoleWithoutPermissionsDto toDto(RoleValue roleValue);
}
