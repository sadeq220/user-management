package ir.bmi.fusion.user_management.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "permission")
@Table(name = "PERMISSION")
@Getter
@Setter
public class PermissionDomain extends AbstractBaseModel {

    private String name;
    @NaturalId
    private String code;
    private String value;

    @Enumerated(EnumType.STRING)
    private PermissionType permissionType;


    @OneToMany(fetch = FetchType.LAZY
            , cascade = CascadeType.ALL,
            mappedBy = "permissionDomain")
    private List<RolePermissionJoinTable> rolePermissionJoinTableList = new ArrayList<>();

    @OneToOne(fetch = FetchType.EAGER) //*-to-one softDeleted associations can't be LAZY fetched
    @JoinColumn(name = "ParentId",referencedColumnName = "code")
    private PermissionDomain parentPermission;

}
