package com.tresit.course.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.tresit.course.dto.CareerDto;
import com.tresit.course.model.Course;
import com.tresit.course.repository.CourseRepository;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final RestTemplate restTemplate;

    public CourseServiceImpl(CourseRepository courseRepository, RestTemplate restTemplate) {
        this.courseRepository = courseRepository;
        this.restTemplate = restTemplate;
    }

    @Value("${student-service.url}")
    private String studentServiceUrl;

    @Override
    @Transactional(readOnly = true)
    public List<Map<String, Object>> getCoursesWithCareers() {
        String url = studentServiceUrl;
        CareerDto[] careers = restTemplate.getForObject(url, CareerDto[].class);

        List<Map<String, Object>> result = new ArrayList<>();

        for (CareerDto career : careers) {
            
            List<Course> matchingCourses = courseRepository.findAll();
            for (Course course : matchingCourses) {
                if ( course.getName().toLowerCase().contains(career.getName().toLowerCase()) ) {
                    Map<String, Object> entry = new HashMap<>();
                    entry.put("career", career.getName());
                    entry.put("course", course.getName());
                    entry.put("students", career.getStudentList());
                    result.add(entry);
                }
            }
        }

        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Course not found. ID: " + id));    
    }

    @Override
    @Transactional
    public Course addCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    @Transactional
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

}
