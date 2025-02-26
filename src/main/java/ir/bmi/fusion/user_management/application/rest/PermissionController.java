package ir.bmi.fusion.user_management.application.rest;

import ir.bmi.fusion.user_management.application.rest.dto.role.PermissionCreationDto;
import ir.bmi.fusion.user_management.application.rest.dto.role.PermissionDto;
import ir.bmi.fusion.user_management.application.rest.dto.role.PermissionDtoMapper;
import ir.bmi.fusion.user_management.domain.port.inbound.PermissionPort;
import ir.bmi.fusion.user_management.domain.port.value.PermissionCreationValue;
import ir.bmi.fusion.user_management.domain.port.value.PermissionValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/permission")
public class PermissionController {
    private final PermissionDtoMapper permissionDtoMapper;
    private final PermissionPort permissionPort;

    public PermissionController(PermissionDtoMapper permissionDtoMapper, PermissionPort permissionPort) {
        this.permissionDtoMapper = permissionDtoMapper;
        this.permissionPort = permissionPort;
    }

    @PostMapping
    public PermissionDto createPermission(@RequestBody PermissionCreationDto permissionCreationDto){
        PermissionCreationValue permissionCreationValue = permissionDtoMapper.toValue(permissionCreationDto);
        PermissionValue permissionValue = permissionPort.addPermission(permissionCreationValue);
        return permissionDtoMapper.toDto(permissionValue);
    }
}
