package ir.bmi.fusion.user_management.application.rest;

import ir.bmi.fusion.user_management.application.rest.dto.role.RoleCreationDto;
import ir.bmi.fusion.user_management.application.rest.dto.role.RoleDto;
import ir.bmi.fusion.user_management.application.rest.dto.role.RoleDtoMapper;
import ir.bmi.fusion.user_management.domain.port.inbound.RolePort;
import ir.bmi.fusion.user_management.domain.port.value.RoleCreationValue;
import ir.bmi.fusion.user_management.domain.port.value.RoleValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/role")
public class RoleController {
    private final RolePort rolePort;
    private final RoleDtoMapper roleDtoMapper;

    public RoleController(RolePort rolePort, RoleDtoMapper roleDtoMapper) {
        this.rolePort = rolePort;
        this.roleDtoMapper = roleDtoMapper;
    }

    @PostMapping
    public RoleDto createRole(@RequestBody RoleCreationDto roleCreationDto){
        RoleCreationValue roleCreationValue = roleDtoMapper.toValue(roleCreationDto);
        RoleValue roleValue = rolePort.addRole(roleCreationValue);
        return roleDtoMapper.toDto(roleValue);
    }
}
