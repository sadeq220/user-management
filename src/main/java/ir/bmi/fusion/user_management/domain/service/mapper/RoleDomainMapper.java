package ir.bmi.fusion.user_management.domain.service.mapper;

import ir.bmi.fusion.user_management.domain.model.RoleDomain;
import ir.bmi.fusion.user_management.domain.port.value.RoleCreationValue;
import ir.bmi.fusion.user_management.domain.port.value.RoleValue;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleDomainMapper {
    RoleValue toValue(RoleDomain roleDomain);

    @Mapping(target = "permissions", ignore = true)
    RoleDomain toDomain(RoleCreationValue roleCreationValue);
}
