package edu.ciukstar.cooper.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author sergiu
 */
@Embeddable
public class Schedule implements Serializable {

    public static Schedule of(LocalDateTime start, LocalDateTime end) {
        return new Schedule(start, end);
    }

    @Column(name = "START_TIME")
    private LocalDateTime startTime;
    @Column(name = "END_TIME")
    private LocalDateTime endTime;

    private Schedule(LocalDateTime start, LocalDateTime end) {
        this.startTime = start;
        this.endTime = end;
    }

    public Schedule() {
        this(null, null);
    }

    @Override
    public String toString() {
        return "Schedule{" + startTime + " - " + endTime + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.startTime);
        hash = 41 * hash + Objects.hashCode(this.endTime);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Schedule other = (Schedule) obj;
        if (!Objects.equals(this.startTime, other.startTime)) {
            return false;
        }
        if (!Objects.equals(this.endTime, other.endTime)) {
            return false;
        }
        return true;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

}
