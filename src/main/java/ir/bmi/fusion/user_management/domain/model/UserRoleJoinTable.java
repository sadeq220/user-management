package ir.bmi.fusion.user_management.domain.model;

import jakarta.persistence.*;



@Entity
@Table(name = "USER_ROLE")
public class UserRoleJoinTable extends AbstractExpirableBaseModel {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ROLE_ID")
    private RoleDomain roleDomain;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    private UserDomain userDomain;

}
