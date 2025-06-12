package com.acme.center.platform.learning.domain.model.entities;

import com.acme.center.platform.learning.domain.model.aggregates.Enrollment;
import com.acme.center.platform.learning.domain.model.valueobjects.ProgressStatus;
import com.acme.center.platform.learning.domain.model.valueobjects.TutorialId;
import com.acme.center.platform.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

/**
 * Represents a record of a students progress on a tutorial of learning path for a course.
 * @summary
 * This entity represents a record of a student's progress on a tutorial
 * It contains information about the enrollment, the tutorial, the status of the progress,
 * and the dates the progress started and completed.
 */
@Entity
@Getter
public class ProgressRecordItem extends AuditableModel {

    @ManyToOne
    @JoinColumn(name = "enrollment_id")
    private Enrollment enrollment;

    private TutorialId tutorialId;

    private ProgressStatus status;

    private Date startedAt;

    private Date completedAt;

    public ProgressRecordItem() {
    }

    public ProgressRecordItem(Enrollment enrollment, TutorialId tutorialId) {
        this.enrollment = enrollment;
        this.tutorialId = tutorialId;
        this.status = ProgressStatus.NOT_STARTED;
    }

    public void start() {
        this.status = ProgressStatus.STARTED;
        this.startedAt = new Date();
    }

    public void complete() {
        this.status = ProgressStatus.COMPLETED;
        this.completedAt = new Date();
    }

    public boolean isComplete() { return  ProgressStatus.COMPLETED.equals(status); }
    public boolean isInProgress() { return  ProgressStatus.STARTED.equals(status); }
    public boolean isNotStarted() { return  ProgressStatus.NOT_STARTED.equals(status); }

    public long calculateDaysElapsed() {
        if (isNotStarted()) return 0;
        var defaultTimeZone = ZoneId.systemDefault();
        var fromDate = this.startedAt.toInstant().atZone(defaultTimeZone);
        var toDate = Objects.isNull(completedAt)
                ? LocalDate.now().atStartOfDay(defaultTimeZone).toInstant()
                : this.completedAt.toInstant();
        return Duration.between(fromDate, toDate).toDays();
    }
}
