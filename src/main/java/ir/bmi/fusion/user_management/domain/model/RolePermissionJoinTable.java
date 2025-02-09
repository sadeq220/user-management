package ir.bmi.fusion.user_management.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "role-permission")
@Table(name = "ROLE_PERMISSION")
@Setter
@Getter
public class RolePermissionJoinTable extends AbstractBaseModel{

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ROLE_ID")
    private RoleDomain roleDomain;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PERMISSION_ID")
    private PermissionDomain permissionDomain;

    @Override
    public int hashCode() {
        return 1; // to ensure consistent hash code between entity state transitions, because id is null when entity is in transient state
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RolePermissionJoinTable)) {
            return false;
        }
        RolePermissionJoinTable rolePermissionJoinTable = (RolePermissionJoinTable) obj;
        return this.getId() != null && this.getId().equals(rolePermissionJoinTable.getId());
    }

}
