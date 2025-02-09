package ir.bmi.fusion.user_management.domain.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "USER")
public class UserDomain extends AbstractBaseModel{

    private String firstName;
    private String lastName;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL,mappedBy = "userDomain")
    private List<UserRoleJoinTable> userRoles = new ArrayList<>();

}
