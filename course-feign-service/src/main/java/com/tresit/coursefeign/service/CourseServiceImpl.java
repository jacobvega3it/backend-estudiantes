package com.tresit.coursefeign.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.client.RestTemplate;

import com.tresit.commons.dto.CareerDto;
import com.tresit.commons.dto.CourseDto;
import com.tresit.coursefeign.client.StudentServiceClient;
import com.tresit.coursefeign.model.Course;
import com.tresit.coursefeign.repository.CourseRepository;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    //private final RestTemplate restTemplate;
    private final StudentServiceClient carrerasFeignClient;

    public CourseServiceImpl(CourseRepository courseRepository, StudentServiceClient studentServiceClient) {
        this.courseRepository = courseRepository;
        this.carrerasFeignClient = studentServiceClient;
    }

    //@Value("${student-service.url}")
    //private String studentServiceUrl;


    @Override
    @Transactional(readOnly = true)
    public List<CourseDto> getAllCoursesWithData() {
        //String url = studentServiceUrl;
        List<CourseDto> result = new ArrayList<>();

        //CareerDto[] careers = restTemplate.getForObject(url, CareerDto[].class);
        CareerDto[] careers = carrerasFeignClient.findAll();

        List<Course> courses = courseRepository.findAll();

        for (Course course : courses) {
            CourseDto courseDto = new CourseDto();
            courseDto.setId(course.getId());
            courseDto.setName(course.getName());
            courseDto.setDescription(course.getDescription());

            for (CareerDto career : careers) {
                if (course.getName().toLowerCase().contains(career.getName().toLowerCase())) {
                    courseDto.setCareer(career);
                    break;
                }
            }

            //courseDto.setCareer(new CareerDto());
            result.add(courseDto);
        }

        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Map<String, Object>> getCoursesWithCareers() {
        //String url = studentServiceUrl;
        //CareerDto[] careers = restTemplate.getForObject(url, CareerDto[].class);
        CareerDto[] careers = carrerasFeignClient.findAll();

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
