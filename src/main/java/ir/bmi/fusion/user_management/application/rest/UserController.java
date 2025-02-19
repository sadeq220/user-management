package ir.bmi.fusion.user_management.application.rest;

import ir.bmi.fusion.user_management.application.rest.dto.UserCreationInputDto;
import ir.bmi.fusion.user_management.application.rest.dto.UserDtoMapper;
import ir.bmi.fusion.user_management.application.rest.dto.UserInfoDto;
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
    public UserCreationInputDto readUserInfo(@RequestParam("id") String id){
        return null;
    }
    @PostMapping
    public UserInfoDto createUser(@RequestBody UserCreationInputDto userCreationInputDto){
        UserCreationValue userCreationValue = userDtoMapper.toValue(userCreationInputDto);
        UserValue userValue = userPort.addUser(userCreationValue);
        return userDtoMapper.toDto(userValue);
    }
}
