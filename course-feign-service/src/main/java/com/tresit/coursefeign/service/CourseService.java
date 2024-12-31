package com.tresit.coursefeign.service;

import java.util.List;
import java.util.Map;

import com.tresit.coursefeign.dto.CourseDto;
import com.tresit.coursefeign.model.Course;

public interface CourseService {

    List<Map<String, Object>> getCoursesWithCareers();
    public List<CourseDto> getAllCoursesWithData();
    public List<Course> getAllCourses();
    public Course getCourseById(Long id);
    public Course addCourse(Course course);
    public void deleteCourse(Long id);

}
