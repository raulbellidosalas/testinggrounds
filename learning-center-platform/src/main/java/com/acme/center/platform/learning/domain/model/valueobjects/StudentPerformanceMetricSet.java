package com.acme.center.platform.learning.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record StudentPerformanceMetricSet(Integer totalCompletedCourses, Integer totalCompletedTutorials) {
    public StudentPerformanceMetricSet() { this(0,0);}

    public StudentPerformanceMetricSet {
        if (totalCompletedCourses == null || totalCompletedCourses < 0)
            throw new IllegalArgumentException("Total completed courses cannot be null or less than 0");
        if (totalCompletedTutorials == null || totalCompletedTutorials < 0)
            throw new IllegalArgumentException("Total completed tutorials cannot be null or less than 0");
    }

    public StudentPerformanceMetricSet incrementTotalCompletedCourses() {
        return new StudentPerformanceMetricSet(totalCompletedCourses + 1, totalCompletedTutorials);
    }

    public StudentPerformanceMetricSet incrementTotalCompletedTutorials() {
        return new StudentPerformanceMetricSet(totalCompletedCourses, totalCompletedTutorials + 1);
    }
}
