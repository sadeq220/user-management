package ir.bmi.fusion.user_management.application.rest.dto;

import ir.bmi.fusion.user_management.domain.port.value.UserCreationValue;
import ir.bmi.fusion.user_management.domain.port.value.UserValue;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserDtoMapper {
    UserInfoDto toDto(UserValue userValue);
    UserCreationValue toValue(UserCreationInputDto userCreationInputDto);
}
