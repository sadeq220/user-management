package ir.bmi.fusion.user_management.infrastructure.adapter.event.stream.mapper;

import ir.bmi.fusion.user_management.domain.port.value.PermissionValue;
import ir.bmi.fusion.user_management.infrastructure.adapter.event.stream.dto.PermissionEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PermissionEventMapper {
    @Mapping(target = "parentId",source = "parentPermission.id")
    PermissionEvent toEvent(PermissionValue permissionValue);
}
