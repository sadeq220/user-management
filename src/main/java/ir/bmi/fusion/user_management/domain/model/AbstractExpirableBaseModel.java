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

    public boolean isExpired() {
        if (toDate == null) {
            return false;
        } else {
            return Instant.now().isAfter(toDate);
        }
    }
}
