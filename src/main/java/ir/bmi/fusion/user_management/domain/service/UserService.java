package ir.bmi.fusion.user_management.domain.service;

import ir.bmi.fusion.user_management.domain.model.AbstractBaseModel;
import ir.bmi.fusion.user_management.domain.model.RoleDomain;
import ir.bmi.fusion.user_management.domain.model.UserDomain;
import ir.bmi.fusion.user_management.domain.port.inbound.UserPort;
import ir.bmi.fusion.user_management.domain.port.outbound.RoleRepository;
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

    public UserService(UserRepository userRepository, UserDomainMapper userDomainMapper, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.userDomainMapper = userDomainMapper;
        this.roleRepository = roleRepository;
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
        return userDomainMapper.toValue(savedUserDomain);
    }

    @Override
    public UserValue getUser(Long id) {
        UserDomain userDomain = userRepository.getUser(id).orElseThrow(() -> new UserNotFoundException(id));
        List<Long> roleIds = userDomain.getRoles().stream().map(AbstractBaseModel::getId).toList();
        roleRepository.getRoles(roleIds);//to fetch all roles in one query
        return userDomainMapper.toValue(userDomain);
    }

}
