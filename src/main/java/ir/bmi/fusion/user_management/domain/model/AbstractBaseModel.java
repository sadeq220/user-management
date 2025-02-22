package ir.bmi.fusion.user_management.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.SoftDelete;
import org.hibernate.annotations.SoftDeleteType;

import java.time.Instant;

/**
 *  *-to-one softDeleted associations can't be LAZY fetched
 *  to support batch insert and update, don't use Identity column id generation
 */
@MappedSuperclass
@SoftDelete(strategy = SoftDeleteType.ACTIVE,columnName = "active")
public abstract class AbstractBaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter
    private Instant createDate;

    @PrePersist
    private void prePersistCallback(){
        this.createDate=Instant.now();
    }

    @Override
    public int hashCode() {
        return 1; // to ensure consistent hash code between entity state transitions, because id is null when entity is in transient state
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AbstractBaseModel)) {
            return false;
        }
        AbstractBaseModel abstractBaseModel = (AbstractBaseModel) obj;
        return this.getId() != null && this.getId().equals(abstractBaseModel.getId());
    }

}
