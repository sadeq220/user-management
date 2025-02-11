package ir.bmi.fusion.user_management.domain.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ROLE")
public class RoleDomain extends AbstractBaseModel {

    private String name;
    private String type;

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "roleDomain",
            cascade = CascadeType.ALL)
    private List<RolePermissionJoinTable> permissions = new ArrayList<>();

    public void addPermission(PermissionDomain permissionDomain){
        RolePermissionJoinTable rolePermissionJoinTable = new RolePermissionJoinTable();
        rolePermissionJoinTable.setPermissionDomain(permissionDomain);
        rolePermissionJoinTable.setRoleDomain(this);
        permissions.add(rolePermissionJoinTable);
    }
}
