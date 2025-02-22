package ir.bmi.fusion.user_management.domain.model;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PreRemove;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@MappedSuperclass
@Setter
@Getter
public abstract class AbstractExpirableBaseModel extends AbstractBaseModel {

    private Instant fromDate;
    private Instant toDate;

    @PreRemove
    private void removeLogic() {
        this.toDate = Instant.now();
    }


    public boolean isExpired() {
        if (toDate == null) {
            return fromDate!=null && Instant.now().isBefore(fromDate);
        } else if (fromDate == null) {
            return Instant.now().isAfter(toDate);
        } else {
            return Instant.now().isAfter(toDate) || Instant.now().isBefore(fromDate);
        }
    }
}
