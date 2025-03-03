package ir.bmi.fusion.user_management.unit;

import ir.bmi.fusion.user_management.domain.model.UserDomain;
import ir.bmi.fusion.user_management.domain.port.inbound.UserPort;
import ir.bmi.fusion.user_management.domain.port.outbound.RoleRepository;
import ir.bmi.fusion.user_management.domain.port.outbound.UserRepository;
import ir.bmi.fusion.user_management.domain.port.value.UserCreationValue;
import ir.bmi.fusion.user_management.domain.port.value.UserValue;
import ir.bmi.fusion.user_management.domain.service.UserService;
import ir.bmi.fusion.user_management.domain.service.mapper.UserDomainMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.List;

public class UserPortTest {

    UserPort userPort;
    UserDomainMapper userDomainMapper;
    UserRepository userRepository;

    @BeforeEach
    void setUp() {
        UserRepository mockedUserRepository = Mockito.mock(UserRepository.class);
        RoleRepository roleRepository = Mockito.mock(RoleRepository.class);
        this.userRepository = mockedUserRepository;
        UserDomainMapper mapper = Mappers.getMapper(UserDomainMapper.class);
        this.userDomainMapper=mapper;
        userPort = new UserService(mockedUserRepository,mapper,roleRepository);
    }
    @Test
    void Add_User_Test(){
        UserCreationValue userCreationValue = new UserCreationValue("test","test","test","test","ssoid","username", List.of());
        UserValue addedUser = userPort.addUser(userCreationValue);
        ArgumentCaptor<UserDomain> userDomainArgumentCaptor = ArgumentCaptor.forClass(UserDomain.class);
        Mockito.verify(userRepository, Mockito.times(1)).saveUser(userDomainArgumentCaptor.capture());
        UserDomain savedUserDomain = userDomainArgumentCaptor.getValue();
        Assertions.assertNull(savedUserDomain.getId());
    }
}
