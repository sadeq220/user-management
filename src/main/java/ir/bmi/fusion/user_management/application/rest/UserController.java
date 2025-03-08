package ir.bmi.fusion.user_management.application.rest;

import ir.bmi.fusion.user_management.application.rest.dto.UserCreationInputDto;
import ir.bmi.fusion.user_management.application.rest.dto.UserDtoMapper;
import ir.bmi.fusion.user_management.application.rest.dto.UserInfoDto;
import ir.bmi.fusion.user_management.application.rest.dto.UserRoleJsonPatch;
import ir.bmi.fusion.user_management.domain.port.inbound.UserPort;
import ir.bmi.fusion.user_management.domain.port.value.UserCreationValue;
import ir.bmi.fusion.user_management.domain.port.value.UserValue;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/user")
public class UserController {
    private final UserPort userPort;
    private final UserDtoMapper userDtoMapper;

    public UserController(UserPort userPort, UserDtoMapper userDtoMapper) {
        this.userPort = userPort;
        this.userDtoMapper = userDtoMapper;
    }
    @GetMapping
    public UserInfoDto readUserInfo(@RequestParam("id") String id){
        UserValue userValue = userPort.getUser(Long.valueOf(id));
        return userDtoMapper.toDto(userValue);
    }
    @PostMapping
    public UserInfoDto createUser(@RequestBody UserCreationInputDto userCreationInputDto){
        UserCreationValue userCreationValue = userDtoMapper.toValue(userCreationInputDto);
        UserValue userValue = userPort.addUser(userCreationValue);
        return userDtoMapper.toDto(userValue);
    }

    @PatchMapping("/role/{userId}")
    public UserInfoDto setUserRoles(@PathVariable("userId") Long userId, @RequestBody UserRoleJsonPatch userRoleJsonPatch) {
        if (userRoleJsonPatch.getOp() == UserRoleJsonPatch.OP.REPLACE) {
            UserValue userValue = userPort.setRoles(userId, userRoleJsonPatch.getRoleIds());
            return userDtoMapper.toDto(userValue);
        } else {
            return null;//TODO complete
        }
    }
}
