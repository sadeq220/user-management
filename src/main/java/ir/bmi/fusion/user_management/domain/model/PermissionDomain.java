package ir.bmi.fusion.user_management.domain.model;

import jakarta.persistence.*;
import org.hibernate.annotations.SoftDelete;
import org.hibernate.annotations.SoftDeleteType;

@Entity(name = "permission")
@Table(name = "PERMISSION")
public class PermissionDomain extends AbstractBaseModel {

    private String name;

}
