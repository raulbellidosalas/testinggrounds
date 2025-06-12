package com.acme.center.platform.learning.domain.model.aggregates;

import com.acme.center.platform.learning.domain.model.events.TutorialCompletedEvent;
import com.acme.center.platform.learning.domain.model.valueobjects.AcmeStudentRecordId;
import com.acme.center.platform.learning.domain.model.valueobjects.EnrollmentStatus;
import com.acme.center.platform.learning.domain.model.valueobjects.ProgressRecord;
import com.acme.center.platform.learning.domain.model.valueobjects.TutorialId;
import com.acme.center.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
public class Enrollment extends AuditableAbstractAggregateRoot<Enrollment> {

    @Getter
    @Embedded
    private AcmeStudentRecordId acmeStudentRecordId;


    @Getter
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @Embedded
    private ProgressRecord progressRecord;

    private EnrollmentStatus status;

    public Enrollment(Course course, AcmeStudentRecordId acmeStudentRecordId) {
        this.course = course;
        this.acmeStudentRecordId = acmeStudentRecordId;
        this.status = EnrollmentStatus.REQUESTED;
        this.progressRecord = new ProgressRecord();
    }

    public Enrollment() {
        // Required by JPA
    }

    public void confirm() {
        this.status = EnrollmentStatus.CONFIRMED;
    }

    public void reject() {
        this.status = EnrollmentStatus.REJECTED;
    }

    public void cancel() {
        this.status = EnrollmentStatus.CANCELLED;
    }

    public String getStatus() {
        return this.status.name().toLowerCase();
    }

    public boolean isConfirmed() {
        return this.status == EnrollmentStatus.CONFIRMED;
    }

    public boolean isRejected() {
        return this.status == EnrollmentStatus.REJECTED;
    }

    public boolean isCancelled() {
        return this.status == EnrollmentStatus.CANCELLED;
    }

    public long calculateDaysElapsed() { return progressRecord.calculateDaysElapsedForEnrollment(this); }

    public void completeTutorial(TutorialId tutorialId) {
         this.progressRecord.completeTutorial(tutorialId, course.getLearningPath());
         this.registerEvent(new TutorialCompletedEvent(this, tutorialId, this.getId()));
    }
}

