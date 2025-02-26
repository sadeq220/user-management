package ir.bmi.fusion.user_management.domain.service;

import ir.bmi.fusion.user_management.domain.model.PermissionDomain;
import ir.bmi.fusion.user_management.domain.port.inbound.PermissionPort;
import ir.bmi.fusion.user_management.domain.port.outbound.PermissionRepository;
import ir.bmi.fusion.user_management.domain.port.value.PermissionCreationValue;
import ir.bmi.fusion.user_management.domain.port.value.PermissionValue;
import ir.bmi.fusion.user_management.domain.service.ex.PermissionNotFoundException;
import ir.bmi.fusion.user_management.domain.service.mapper.PermissionDomainMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class PermissionService implements PermissionPort {
    private final PermissionRepository permissionRepository;
    private final PermissionDomainMapper permissionDomainMapper;

    public PermissionService(PermissionRepository permissionRepository, PermissionDomainMapper permissionDomainMapper) {
        this.permissionRepository = permissionRepository;
        this.permissionDomainMapper = permissionDomainMapper;
    }

    @Override
    public PermissionValue addPermission(PermissionCreationValue permissionCreationValue) {
        PermissionDomain domain = permissionDomainMapper.toDomain(permissionCreationValue);
        if (permissionCreationValue.parentId() != null) {
            Optional<PermissionDomain> parentPermissionOptional = permissionRepository.get(permissionCreationValue.parentId());
            PermissionDomain parentPermission = parentPermissionOptional.orElseThrow(() -> new PermissionNotFoundException(permissionCreationValue.parentId()));
            domain.setParentPermission(parentPermission);
        }
        PermissionDomain savedDomain = permissionRepository.save(domain);
        return permissionDomainMapper.toValue(savedDomain);
    }
}
