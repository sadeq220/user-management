package ir.bmi.fusion.user_management.domain.service;

import ir.bmi.fusion.user_management.domain.model.UserDomain;
import ir.bmi.fusion.user_management.domain.port.inbound.UserPort;
import ir.bmi.fusion.user_management.domain.port.outbound.UserRepository;
import ir.bmi.fusion.user_management.domain.port.value.UserValue;
import ir.bmi.fusion.user_management.domain.service.mapper.UserDomainMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService implements UserPort {
    private final UserRepository userRepository;
    private final UserDomainMapper userDomainMapper;

    public UserService(UserRepository userRepository, UserDomainMapper userDomainMapper) {
        this.userRepository = userRepository;
        this.userDomainMapper = userDomainMapper;
    }

    @Override
    public UserValue addUser(UserValue userValue) {
        UserDomain userDomain = userDomainMapper.toDomain(userValue);
        UserDomain savedUserDomain = userRepository.saveUser(userDomain);
        return userDomainMapper.toValue(savedUserDomain);
    }
}
