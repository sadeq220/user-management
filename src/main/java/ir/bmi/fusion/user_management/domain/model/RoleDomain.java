package ir.bmi.fusion.user_management.domain.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ROLE")
@Setter
@Getter
public class RoleDomain extends AbstractBaseModel {

    private String name;
    private String code;

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "roleDomain",
            cascade = CascadeType.ALL)
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private List<RolePermissionJoinTable> permissions = new ArrayList<>();

    public void addPermission(PermissionDomain permissionDomain){
        if (permissionDomain == null){
            return;
        }
        if (getPermissions().contains(permissionDomain)) {
            return;
        }
        RolePermissionJoinTable rolePermissionJoinTable = new RolePermissionJoinTable();
        rolePermissionJoinTable.setPermissionDomain(permissionDomain);
        rolePermissionJoinTable.setRoleDomain(this);
        permissions.add(rolePermissionJoinTable);

        this.addPermission(permissionDomain.getParentPermission());//recursive adding
    }
    public List<PermissionDomain> getPermissions(){
        return permissions.stream()
                .filter(joinTable-> !joinTable.isExpired())
                .map(RolePermissionJoinTable::getPermissionDomain)
                .toList();
    }
}
