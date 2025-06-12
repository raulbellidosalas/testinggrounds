package com.acme.center.platform.learning.domain.model.events;

import com.acme.center.platform.learning.domain.model.valueobjects.TutorialId;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class TutorialCompletedEvent extends ApplicationEvent {
    private final TutorialId tutorialId;
    private final Long enrollmentId;

    public TutorialCompletedEvent(Object source, TutorialId tutorialId, Long enrollmentId) {
        super(source);
        this.tutorialId = tutorialId;
        this.enrollmentId = enrollmentId;
    }
}
