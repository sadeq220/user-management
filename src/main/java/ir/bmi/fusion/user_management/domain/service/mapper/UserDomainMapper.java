package ir.bmi.fusion.user_management.domain.service.mapper;

import ir.bmi.fusion.user_management.domain.model.UserDomain;
import ir.bmi.fusion.user_management.domain.port.value.UserCreationValue;
import ir.bmi.fusion.user_management.domain.port.value.UserValue;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",uses = {PermissionDomainMapper.class,RoleDomainMapper.class})
public interface UserDomainMapper {
    @Mapping(target = "userRoles",ignore = true)
    @Mapping(target = "roles",ignore = true)
    @Mapping(target="permissions",ignore = true)
    UserDomain toDomain(UserCreationValue userCreationValue);
    @Mapping(target = "permissions",source = "permissions")
    UserValue toValue(UserDomain userDomain);
}
