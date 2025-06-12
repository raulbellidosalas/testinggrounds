package com.acme.center.platform.learning.domain.exceptions;

public class CourseNotFoundException extends RuntimeException {

    public CourseNotFoundException(Long courseId) {
        super(String.format("Course with id %s not found", courseId));
    }
}
