package com.acme.center.platform.learning.domain.model.valueobjects;

import com.acme.center.platform.learning.domain.model.aggregates.Course;
import com.acme.center.platform.learning.domain.model.entities.LearningPathItem;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToMany;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

@Embeddable
public class LearningPath {

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<LearningPathItem> learningPathItems;

    public LearningPath() { this.learningPathItems = List.of(); }

    private LearningPathItem getLearningPathItemWithId(Long itemId) {
        return this.getFirstLearningPathItemWhere( item -> item.getId().equals(itemId));
    }

    private LearningPathItem getFirstLearningPathItemWhere(Predicate<LearningPathItem> predicate) {
        return learningPathItems.stream()
                .filter(predicate)
                .findFirst()
                .orElse(null);
    }

    public LearningPathItem getLearningPathItemWithTutorialId(TutorialId tutorialIdd) {
        return this.getFirstLearningPathItemWhere( item -> item.getTutorialId().equals(tutorialIdd));
    }

    public TutorialId getFirstTutorialInLearningPath() { return learningPathItems.getFirst().getTutorialId();}

    public boolean isLastTutorialInLearningPath(TutorialId currentTutorialId) {
        return Objects.isNull(getNextTutorialInLearningPath(currentTutorialId));
    }

    public TutorialId getNextTutorialInLearningPath(TutorialId currentTutorialId) {
        LearningPathItem nextItem = getLearningPathItemWithTutorialId(currentTutorialId).getNextItem();
        return !Objects.isNull(nextItem) ? nextItem.getTutorialId() : null;
    }

    public LearningPathItem getLastItemInLearningPath() {
        return this.getFirstLearningPathItemWhere(item -> Objects.isNull(item.getNextItem()));
    }

    public boolean isEmpty() { return  learningPathItems.isEmpty(); }

    public void addItem(Course course, TutorialId tutorialId, LearningPathItem nextItem) {
        // Add the new item to the learning path before the next item
        LearningPathItem learningPathItem = new LearningPathItem(course, tutorialId, nextItem);
        learningPathItems.add(learningPathItem);
    }

    public void addItem(Course course, TutorialId tutorialId) {
        // Add the new item to the end of the learning path
        LearningPathItem learningPathItem = new LearningPathItem(course, tutorialId, null);
        LearningPathItem originalLastItem = null;
        if (!isEmpty()) originalLastItem = getLastItemInLearningPath();
        learningPathItems.add(learningPathItem);
        if (!Objects.isNull(originalLastItem)) originalLastItem.updateNextItem(learningPathItem);
    }

    public void addItem(Course course, TutorialId tutorialId, TutorialId nextTutorialId) {
        // Add the new item to the learning path before the next item
        LearningPathItem nextItem = getLearningPathItemWithTutorialId(nextTutorialId);
        addItem(course, tutorialId, nextItem);
    }
}
