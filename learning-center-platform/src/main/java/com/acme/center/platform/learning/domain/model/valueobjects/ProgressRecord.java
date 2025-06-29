package com.acme.center.platform.learning.domain.model.valueobjects;

import com.acme.center.platform.learning.domain.model.aggregates.Enrollment;
import com.acme.center.platform.learning.domain.model.entities.ProgressRecordItem;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;


@Embeddable
public class ProgressRecord {

    @OneToMany(mappedBy = "enrollment", cascade = CascadeType.ALL)
    private List<ProgressRecordItem> progressRecordItems;

    public ProgressRecord() {
        progressRecordItems = new ArrayList<>();
    }

    public void initializeProgressRecord(Enrollment enrollment, LearningPath learningPath) {
        if (learningPath.isEmpty())return;
        TutorialId tutorialId = learningPath.getFirstTutorialInLearningPath();
        ProgressRecordItem progressRecordItem = new ProgressRecordItem(enrollment, tutorialId);
        progressRecordItems.add(progressRecordItem);
    }

    private ProgressRecordItem getProgressRecordItemWithTutorialId(TutorialId tutorialId) {
        return progressRecordItems.stream()
                .filter(progressRecordItem -> progressRecordItem.getTutorialId().equals(tutorialId))
                .findFirst()
                .orElse(null);
    }

    private boolean hasAnItemInProgress() {
        return progressRecordItems.stream().anyMatch(ProgressRecordItem::isInProgress);
    }

    public void startTutorial(TutorialId tutorialId) {
        if (hasAnItemInProgress()) throw new IllegalArgumentException("A tutorial is already in progress.");
        ProgressRecordItem progressRecordItem = getProgressRecordItemWithTutorialId(tutorialId);
        if (progressRecordItem != null) {
            if (progressRecordItem.isNotStarted()) progressRecordItem.start();
            else throw new IllegalArgumentException("The tutorial is already started or completed.");
        }
        else throw new IllegalArgumentException("The tutorial is not found in the progress record.");
    }


    public void completeTutorial(TutorialId tutorialId, LearningPath learningPath) {
        ProgressRecordItem progressRecordItem = getProgressRecordItemWithTutorialId(tutorialId);
        if (progressRecordItem != null)  progressRecordItem.complete();
        else throw new IllegalArgumentException("The tutorial is not found in the progress record.");

        if (learningPath.isLastTutorialInLearningPath(tutorialId)) return;
        TutorialId nextTutorialId = learningPath.getNextTutorialInLearningPath(tutorialId);
        if (nextTutorialId != null) {
            ProgressRecordItem nextProgressRecordItem = new ProgressRecordItem(progressRecordItem.getEnrollment(), nextTutorialId);
            progressRecordItems.add(nextProgressRecordItem);
        }
    }

    public long calculateDaysElapsedForEnrollment(Enrollment enrollment) {
        return progressRecordItems.stream()
                .filter(progressRecordItem -> progressRecordItem.getEnrollment().equals(enrollment))
                .mapToLong(ProgressRecordItem::calculateDaysElapsed)
                .sum();
    }

}
