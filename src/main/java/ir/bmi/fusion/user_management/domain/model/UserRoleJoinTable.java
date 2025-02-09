package ir.bmi.fusion.user_management.domain.model;

import jakarta.persistence.*;
import org.hibernate.annotations.SoftDelete;
import org.hibernate.annotations.SoftDeleteType;

import java.time.Instant;

/**
 * aggregate root
 */
@Entity
@Table(name = "USER_ROLE")
public class UserRoleJoinTable extends AbstractBaseModel {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ROLE_ID")
    private RoleDomain roleDomain;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    private UserDomain userDomain;

    private Instant fromDate;
    private Instant toDate;

    @PrePersist
    private void setup(){
        fromDate=Instant.now();
    }
}
