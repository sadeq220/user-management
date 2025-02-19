package ir.bmi.fusion.user_management.domain.service.mapper;

import ir.bmi.fusion.user_management.domain.model.UserDomain;
import ir.bmi.fusion.user_management.domain.port.value.UserCreationValue;
import ir.bmi.fusion.user_management.domain.port.value.UserValue;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserDomainMapper {
    @Mapping(target = "userRoles",ignore = true)
    @Mapping(target = "roles",ignore = true)
    UserDomain toDomain(UserCreationValue userCreationValue);
    UserValue toValue(UserDomain userDomain);
}
