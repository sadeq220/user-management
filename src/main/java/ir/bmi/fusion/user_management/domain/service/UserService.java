package ir.bmi.fusion.user_management.domain.service;

import ir.bmi.fusion.user_management.domain.model.AbstractBaseModel;
import ir.bmi.fusion.user_management.domain.model.RoleDomain;
import ir.bmi.fusion.user_management.domain.model.UserDomain;
import ir.bmi.fusion.user_management.domain.port.inbound.UserPort;
import ir.bmi.fusion.user_management.domain.port.outbound.RoleRepository;
import ir.bmi.fusion.user_management.domain.port.outbound.UserEventPublisher;
import ir.bmi.fusion.user_management.domain.port.outbound.UserRepository;
import ir.bmi.fusion.user_management.domain.port.value.UserCreationValue;
import ir.bmi.fusion.user_management.domain.port.value.UserValue;
import ir.bmi.fusion.user_management.domain.service.ex.RoleNotFoundException;
import ir.bmi.fusion.user_management.domain.service.ex.UserNotFoundException;
import ir.bmi.fusion.user_management.domain.service.mapper.UserDomainMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService implements UserPort {
    private final UserRepository userRepository;
    private final UserDomainMapper userDomainMapper;
    private final RoleRepository roleRepository;
    private final UserEventPublisher userEventPublisher;

    public UserService(UserRepository userRepository,
                       UserDomainMapper userDomainMapper,
                       RoleRepository roleRepository,
                       UserEventPublisher userEventPublisher) {
        this.userRepository = userRepository;
        this.userDomainMapper = userDomainMapper;
        this.roleRepository = roleRepository;
        this.userEventPublisher = userEventPublisher;
    }

    @Override
    public UserValue addUser(UserCreationValue userCreationValue) {
        UserDomain userDomain = userDomainMapper.toDomain(userCreationValue);
        List<RoleDomain> userRoles = userCreationValue
                .roleIds()
                .stream()
                .map(roleId -> roleRepository
                        .getRole(roleId)
                        .orElseThrow(() -> new RoleNotFoundException(roleId)))
                .toList();
        userRoles.forEach(userDomain::addRole);
        UserDomain savedUserDomain = userRepository.saveUser(userDomain);
        UserValue userValue = userDomainMapper.toValue(savedUserDomain);
        userEventPublisher.userCreated(userValue);
        return userValue;
    }

    @Override
    public UserValue getUser(Long id) {
        UserDomain userDomain = this.getUserDomain(id);
        return userDomainMapper.toValue(userDomain);
    }

    @Override
    public UserValue setRoles(Long id, List<Long> roleIds) {
        List<RoleDomain> roleDomains = roleRepository.getRoles(roleIds);
        UserDomain userDomain = getUserDomain(id);
        userDomain.setUserRoles(roleDomains);
        return userDomainMapper.toValue(userDomain);
    }
    private UserDomain getUserDomain(Long id){
        UserDomain userDomain = userRepository.getUser(id).orElseThrow(() -> new UserNotFoundException(id));
        List<Long> roleIds = userDomain.getRoles().stream().map(AbstractBaseModel::getId).toList();
        roleRepository.getRoles(roleIds);//to fetch all roles in one query
        return userDomain;
    }
}
