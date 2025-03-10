package ir.bmi.fusion.user_management.infrastructure.adapter.event.stream.mapper;

import ir.bmi.fusion.user_management.domain.port.value.RoleValue;
import ir.bmi.fusion.user_management.infrastructure.adapter.event.stream.dto.RoleEvent;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleEventMapper {
    RoleEvent toEvent(RoleValue roleValue);
}
