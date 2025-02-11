package ir.bmi.fusion.user_management.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "role-permission")
@Table(name = "ROLE_PERMISSION")
@Setter
@Getter
public class RolePermissionJoinTable extends AbstractExpirableBaseModel{

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ROLE_ID")
    private RoleDomain roleDomain;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PERMISSION_ID")
    private PermissionDomain permissionDomain;
}
