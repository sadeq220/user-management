package ir.bmi.fusion.user_management.infrastructure.adapter.event.stream.mapper;

import ir.bmi.fusion.user_management.domain.port.value.UserValue;
import ir.bmi.fusion.user_management.infrastructure.adapter.event.stream.dto.UserEvent;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {RoleEventMapper.class, PermissionEventMapper.class})
public interface UserEventMapper {
    UserEvent toEvent(UserValue userValue);
}
