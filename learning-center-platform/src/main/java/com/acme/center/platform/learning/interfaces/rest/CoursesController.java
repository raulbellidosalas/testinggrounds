package com.acme.center.platform.learning.interfaces.rest;

import com.acme.center.platform.learning.domain.model.aggregates.Course;
import com.acme.center.platform.learning.domain.model.queries.GetCourseByIdQuery;
import com.acme.center.platform.learning.domain.services.CourseCommandService;
import com.acme.center.platform.learning.domain.services.CourseQueryService;
import com.acme.center.platform.learning.interfaces.rest.resources.CourseResource;
import com.acme.center.platform.learning.interfaces.rest.resources.CreateCourseResource;
import com.acme.center.platform.learning.interfaces.rest.transform.CourseResourceFromEntityAssembler;
import com.acme.center.platform.learning.interfaces.rest.transform.CreateCourseCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/courses", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Courses", description = "Operations related to courses")
public class CoursesController {
    private final CourseCommandService courseCommandService;
    private final CourseQueryService courseQueryService;

    public CoursesController(CourseCommandService courseCommandService, CourseQueryService courseQueryService) {
        this.courseCommandService = courseCommandService;
        this.courseQueryService = courseQueryService;
    }

    @PostMapping
    public ResponseEntity<CourseResource> createCourse(@RequestBody CreateCourseResource resource) {
        var createCourseCommand = CreateCourseCommandFromResourceAssembler.toCommandFromResource(resource);
        var courseId = courseCommandService.handle(createCourseCommand);
        if (courseId == null || courseId == 0L) return ResponseEntity.badRequest().build();
        var getCourseByIdQuery = new GetCourseByIdQuery(courseId);
        var course = courseQueryService.handle(getCourseByIdQuery);
        if (course.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var courseEntity = course.get();
        var courseResource = CourseResourceFromEntityAssembler.toResourceFromEntity(courseEntity);
        return new ResponseEntity<>(courseResource, HttpStatus.CREATED);
    }
}
