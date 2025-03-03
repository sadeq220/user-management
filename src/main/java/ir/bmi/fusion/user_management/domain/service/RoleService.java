package ir.bmi.fusion.user_management.domain.service;

import ir.bmi.fusion.user_management.domain.model.PermissionDomain;
import ir.bmi.fusion.user_management.domain.model.RoleDomain;
import ir.bmi.fusion.user_management.domain.port.inbound.RolePort;
import ir.bmi.fusion.user_management.domain.port.outbound.PermissionRepository;
import ir.bmi.fusion.user_management.domain.port.outbound.RoleRepository;
import ir.bmi.fusion.user_management.domain.port.value.RoleCreationValue;
import ir.bmi.fusion.user_management.domain.port.value.RoleValue;
import ir.bmi.fusion.user_management.domain.service.mapper.RoleDomainMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleService implements RolePort {
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final RoleDomainMapper roleDomainMapper;

    public RoleService(RoleRepository roleRepository, PermissionRepository permissionRepository, RoleDomainMapper roleDomainMapper) {
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
        this.roleDomainMapper = roleDomainMapper;
    }

    @Override
    public RoleValue addRole(RoleCreationValue roleCreationValue) {
        List<Long> permissionIds = roleCreationValue.permissionIds();
        List<PermissionDomain> permissionDomains = permissionIds.stream()
                .map(permissionRepository::get)
                .map(permissionDomainOptional -> permissionDomainOptional.orElseThrow(()->new RuntimeException("")) )//TODO change exception to business exception
                .toList();
        RoleDomain roleDomain = roleDomainMapper.toDomain(roleCreationValue);
        permissionDomains.forEach(roleDomain::addPermission);

        RoleDomain savedRoleDomain = roleRepository.saveRole(roleDomain);
        return roleDomainMapper.toValue(savedRoleDomain);
    }
}
