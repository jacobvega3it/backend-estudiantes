package com.tresit.course.repository;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tresit.course.model.Course;

@ExtendWith(MockitoExtension.class)
public class CourseRepositoryTest {

    // Se crea un mock de la clase CourseRepository
    // Un mock es un objeto simulado que simula el comportamiento de un objeto real
    @Mock 
    private CourseRepository courseRepository;

    private Course course;

    @BeforeEach
    public void setUp() {
        course = new Course();
        course.setId(1L);
        course.setName("Java");
        course.setDescription("Java Course");
    }

    @Test
    public void whenFindById_thenReturnCourse() {
        // Se configura el mock para que cuando se llame al método findById con el id 1L, retorne el objeto course
        when(courseRepository.findById(1L)).thenReturn(java.util.Optional.of(course));

        // Se ejecuta el método findById con el id 1L
        Course result = courseRepository.findById(1L).get();

        // Se verifica que el resultado sea el esperado
        assert(result.getId() == 1L);
        assert(result.getName().equals("Java"));
        assert(result.getDescription().equals("Java Course"));
    }

    @Test
    void whenFindAll_thenReturnCoursesList() {
        // Se configura el mock para que cuando se llame al método findAll, retorne una lista con el objeto course
        when(courseRepository.findAll()).thenReturn(java.util.Arrays.asList(course));

        // Se ejecuta el método findAll
        java.util.List<Course> result = courseRepository.findAll();

        // Se verifica que el resultado sea el esperado
        assert(result.size() == 1);
        assert(result.get(0).getId() == 1L);
        assert(result.get(0).getName().equals("Java"));
        assert(result.get(0).getDescription().equals("Java Course"));
    }

    @Test
    void whenSaveCourse_thenReturnCourse() {
        // Se configura el mock para que cuando se llame al método save con el objeto course, retorne el objeto course
        when(courseRepository.save(course)).thenReturn(course);

        // Se ejecuta el método save con el objeto course
        Course result = courseRepository.save(course);

        // Se verifica que el resultado sea el esperado
        assert(result.getId() == 1L);
        assert(result.getName().equals("Java"));
        assert(result.getDescription().equals("Java Course"));
    }
}
