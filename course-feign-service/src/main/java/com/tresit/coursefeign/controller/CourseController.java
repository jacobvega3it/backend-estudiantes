package com.tresit.coursefeign.controller;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tresit.commons.dto.CourseDto;
import com.tresit.coursefeign.model.Course;
import com.tresit.coursefeign.service.CourseService;

@RefreshScope
@RestController
@RequestMapping
public class CourseController {

    private final CourseService service;

    public CourseController(CourseService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses(@RequestParam(name = "name", required = false) String name, 
        @RequestHeader(name = "token-request", required = false) String token) {
            System.out.println("Nombre: " + name);
            System.out.println("Token: " + token);

        return ResponseEntity.ok(service.getAllCourses());
    }
    
    @GetMapping("/carreras")
    public ResponseEntity<List<Map<String, Object>>> getCoursesWithCareers() {
        return ResponseEntity.ok(service.getCoursesWithCareers());
    }

    @GetMapping("/carrerasdatos")
    public ResponseEntity<List<CourseDto>> getCoursesWithData() {
        return ResponseEntity.ok(service.getAllCoursesWithData());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getCourseById(id));
    }

    @PostMapping
    public ResponseEntity<Course> addCourse(@RequestBody Course course) {
        return ResponseEntity.ok(service.addCourse(course));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        service.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }

}
