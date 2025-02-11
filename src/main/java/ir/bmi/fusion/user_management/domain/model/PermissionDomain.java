package ir.bmi.fusion.user_management.domain.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "permission")
@Table(name = "PERMISSION")
public class PermissionDomain extends AbstractBaseModel {

    private String name;

    @OneToMany(fetch = FetchType.LAZY
            , cascade = CascadeType.ALL,
            mappedBy = "permissionDomain")
    private List<RolePermissionJoinTable> rolePermissionJoinTableList = new ArrayList<>();


}
