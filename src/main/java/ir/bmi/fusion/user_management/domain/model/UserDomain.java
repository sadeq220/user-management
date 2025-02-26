package ir.bmi.fusion.user_management.domain.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "userDomain")
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
    private void addRoleWithoutCheck(RoleDomain roleDomain){
        UserRoleJoinTable userRoleJoinTable = new UserRoleJoinTable();
        userRoleJoinTable.setRoleDomain(roleDomain);
        userRoleJoinTable.setUserDomain(this);
        userRoles.add(userRoleJoinTable);
    }
    public void setUserRoles(List<RoleDomain> roleDomains){
        List<RoleDomain> currentRoles = this.getRoles();
        for (RoleDomain roleDomain: roleDomains){
            if (!currentRoles.contains(roleDomain)) {
                addRoleWithoutCheck(roleDomain);
            }
        }
    }

}
