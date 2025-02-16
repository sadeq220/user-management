package ir.bmi.fusion.user_management.unit;

import ir.bmi.fusion.user_management.domain.model.UserDomain;
import ir.bmi.fusion.user_management.domain.port.inbound.UserPort;
import ir.bmi.fusion.user_management.domain.port.outbound.UserRepository;
import ir.bmi.fusion.user_management.domain.port.value.UserValue;
import ir.bmi.fusion.user_management.domain.service.UserService;
import ir.bmi.fusion.user_management.domain.service.mapper.UserDomainMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

public class UserPortTest {

    UserPort userPort;
    UserDomainMapper userDomainMapper;
    UserRepository userRepository;

    @BeforeEach
    void setUp() {
        UserRepository mockedUserRepository = Mockito.mock(UserRepository.class);
        this.userRepository = mockedUserRepository;
        UserDomainMapper mapper = Mappers.getMapper(UserDomainMapper.class);
        this.userDomainMapper=mapper;
        userPort = new UserService(mockedUserRepository,mapper);
    }
    @Test
    void Add_User_Test(){
        UserValue userValue = new UserValue(1L,"test","test");
        UserValue addedUser = userPort.addUser(userValue);
        ArgumentCaptor<UserDomain> userDomainArgumentCaptor = ArgumentCaptor.forClass(UserDomain.class);
        Mockito.verify(userRepository, Mockito.times(1)).saveUser(userDomainArgumentCaptor.capture());
        UserDomain savedUserDomain = userDomainArgumentCaptor.getValue();
        Assertions.assertNull(savedUserDomain.getId());
    }
}
