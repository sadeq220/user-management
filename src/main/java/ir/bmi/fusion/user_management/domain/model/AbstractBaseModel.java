package ir.bmi.fusion.user_management.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.SoftDelete;
import org.hibernate.annotations.SoftDeleteType;

/**
 *  *-to-one softDeleted associations cann't be LAZY fetched
 */
@MappedSuperclass
@SoftDelete(strategy = SoftDeleteType.ACTIVE,columnName = "active")
public abstract class AbstractBaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Column(insertable=false, updatable=false)
    private Boolean active;

}
