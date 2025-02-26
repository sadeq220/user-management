package ir.bmi.fusion.user_management.domain.service.mapper;

import ir.bmi.fusion.user_management.domain.model.PermissionDomain;
import ir.bmi.fusion.user_management.domain.model.PermissionType;
import ir.bmi.fusion.user_management.domain.port.value.PermissionCreationValue;
import ir.bmi.fusion.user_management.domain.port.value.PermissionValue;
import ir.bmi.fusion.user_management.domain.service.ex.PermissionTypeNotValidException;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PermissionDomainMapper {
    @Mapping(target = "permissionType",source = "type")
    @Mapping(target = "rolePermissionJoinTableList",ignore = true)
    @Mapping(target = "parentPermission", ignore = true)
    PermissionDomain toDomain(PermissionCreationValue permissionCreationValue);
    PermissionValue toValue(PermissionDomain permissionDomain);

    default PermissionType convert(String permissionType){
        try {
            return PermissionType.valueOf(permissionType);
        } catch (IllegalArgumentException illegalArgumentException){
            throw new PermissionTypeNotValidException();
        }
    }
}
