package com.acme.center.platform.learning.domain.model.aggregates;

import com.acme.center.platform.learning.domain.model.commands.CreateCourseCommand;
import com.acme.center.platform.learning.domain.model.valueobjects.LearningPath;
import com.acme.center.platform.learning.domain.model.valueobjects.TutorialId;
import com.acme.center.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;

@Entity
@Getter
public class Course extends AuditableAbstractAggregateRoot<Course> {

    private String title;
    private String description;

    @Embedded
    private final LearningPath learningPath;


    public Course() {
        this.title = Strings.EMPTY;
        this.description = Strings.EMPTY;
        learningPath = new LearningPath();
    }

    public Course(CreateCourseCommand command) {
        this.title = command.title();
        this.description = command.description();
        learningPath = new LearningPath();
    }


    public Course updateInformation(String title, String description) {
        this.title = title;
        this.description = description;
        return this;
    }

    public void addTutorialToLearningPath(TutorialId tutorialId) { this.learningPath.addItem(this, tutorialId); }

    public void addTutorialToLearningPath(TutorialId tutorialId, TutorialId nextTutorialId) {
        this.learningPath.addItem(this, tutorialId, nextTutorialId);
    }
}
