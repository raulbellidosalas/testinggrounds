package com.acme.center.platform.learning.domain.model.valueobjects;


import jakarta.persistence.Embeddable;

/**
 * TutorialId value object
 * @summary
 * This value object represents the unique identifier of tutorial.
 * The tutorialId must be greater than 0. It throws on IllegalArgumentException
 * if TutorialId is null or less than or equal to zero
 * @param tutorialId
 */
@Embeddable
public record TutorialId(Long tutorialId) {

    public TutorialId {
        if (tutorialId == null || tutorialId < 0) {
            throw new IllegalArgumentException("TutorialId cannot null or less than or equal to zero");
        }
    }
}
