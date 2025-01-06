package com.tresit.course.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tresit.course.model.Course;
import com.tresit.course.repository.CourseRepository;

@ExtendWith(MockitoExtension.class)
public class CourseServiceTest {

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private CourseServiceImpl courseService;

    @Test
    void whenGetAllCourses_thenReturnsCoursesList() {
        // Preparación de datos 
        Course course = new Course();
        course.setId(1L);
        course.setName("Java");
        course.setDescription("Java Course");
        List<Course> courses = Arrays.asList(course);

        // Configurar Mock
        when(courseRepository.findAll()).thenReturn(courses);

        // Ejecutar método a probar
        List<Course> result = courseService.getAllCourses();

        // Verificar resultado
        assertEquals(1, result.size());
        assertEquals("Java", result.get(0).getName());
    }

    @Test
    void whenGetCourseById_thenReturnsCourse() {
        // Preparación de datos
        Course course = new Course();
        course.setId(1L);
        course.setName("Java");
        course.setDescription("Java Course");

        // Configurar Mock
        when(courseRepository.findById(1L)).thenReturn(java.util.Optional.of(course));

        // Ejecutar método a probar
        Course result = courseService.getCourseById(1L);

        // Verificar resultado
        assertEquals("Java", result.getName());
    }

    @Test
    void whenAddCourse_thenReturnsCourse() {
        // Preparación de datos
        Course course = new Course();
        course.setId(1L);
        course.setName("Java");
        course.setDescription("Java Course");

        // Configurar Mock
        when(courseRepository.save(course)).thenReturn(course);

        // Ejecutar método a probar
        Course result = courseService.addCourse(course);

        // Verificar resultado
        assertEquals("Java", result.getName());
    }

}
