package ir.bmi.fusion.user_management.domain.service.mapper;

import ir.bmi.fusion.user_management.domain.model.PermissionDomain;
import ir.bmi.fusion.user_management.domain.model.RoleDomain;
import ir.bmi.fusion.user_management.domain.port.value.PermissionValue;
import ir.bmi.fusion.user_management.domain.port.value.RoleValue;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleDomainMapper {
    RoleValue toValue(RoleDomain roleDomain);

    PermissionValue toValue(PermissionDomain permissionDomain);
}
