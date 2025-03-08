package ir.bmi.fusion.user_management.domain.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * aggregate root
 */
@Entity
@Table(name = "USER")
@Getter
@Setter
public class UserDomain extends AbstractBaseModel{

    private String firstName;
    private String lastName;
    private String nationalCode;
    private String personalCode;
    private String ssoId;
    private String username;

    @Setter(AccessLevel.NONE)
    private Instant createDate;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "userDomain",orphanRemoval = true)
    private List<UserRoleJoinTable> userRoles = new ArrayList<>();

    public List<RoleDomain> getRoles(){
        return this.userRoles.stream()
                .filter(userRoleJoinTable -> !userRoleJoinTable.isExpired())
                .map(UserRoleJoinTable::getRoleDomain).toList();
    }
    public void addRole(RoleDomain roleDomain){
        if (this.getRoles().contains(roleDomain)) {
            return;
        } else {
            addRoleWithoutCheck(roleDomain);
        }
    }
    public Set<PermissionDomain> getPermissions(){
        return this.getRoles()
                .stream()
                .flatMap(roleDomain -> roleDomain.getPermissions().stream())
                .collect(Collectors.toSet());
    }
    private void addRoleWithoutCheck(RoleDomain roleDomain){
        UserRoleJoinTable userRoleJoinTable = new UserRoleJoinTable();
        userRoleJoinTable.setRoleDomain(roleDomain);
        userRoleJoinTable.setUserDomain(this);
        userRoles.add(userRoleJoinTable);
    }
    public void setUserRoles(List<RoleDomain> newRoles){
        List<RoleDomain> shouldAddedRoles= new ArrayList<>(newRoles);
        List<UserRoleJoinTable> shouldRemovedRoles= new ArrayList<>();
        this.userRoles.forEach(joinTable->{
            if (newRoles.contains(joinTable.getRoleDomain())){
                shouldAddedRoles.remove(joinTable.getRoleDomain());
            } else {
                joinTable.setUserDomain(null);
                shouldRemovedRoles.add(joinTable);

            }
                });
        this.userRoles.removeAll(shouldRemovedRoles);
        for (RoleDomain roleDomain: shouldAddedRoles){
                addRoleWithoutCheck(roleDomain);
        }
    }

}
