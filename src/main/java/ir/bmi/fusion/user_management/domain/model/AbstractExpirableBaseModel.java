package ir.bmi.fusion.user_management.domain.model;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;

import java.time.Instant;

@MappedSuperclass
public abstract class AbstractExpirableBaseModel extends AbstractBaseModel {
    private Instant fromDate;
    private Instant toDate;

    @PreRemove
    private void removeLogic() {
        this.toDate = Instant.now();
    }

    @PrePersist
    private void setup(){
        fromDate=Instant.now();
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
        if (!(obj instanceof UserRoleJoinTable)) {
            return false;
        }
        UserRoleJoinTable userRoleJoinTable = (UserRoleJoinTable) obj;
        return this.getId() != null && this.getId().equals(userRoleJoinTable.getId());
    }
}
