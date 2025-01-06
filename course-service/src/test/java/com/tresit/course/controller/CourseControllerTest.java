package com.tresit.course.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.autoconfigure.RefreshAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tresit.course.model.Course;
import com.tresit.course.service.CourseService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

@WebMvcTest(CourseController.class)
@Import(RefreshAutoConfiguration.class)
public class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CourseService courseService;

    @Test
    void whenGetAllCourses_thenReturnsCoursesList() throws Exception {

        //Preparación de datos
        Course course = new Course();
        course.setId(1L);
        course.setName("Java");
        course.setDescription("Java Course");
        List<Course> courses = Arrays.asList(course);
        
        // Configurar Mock
        when(courseService.getAllCourses()).thenReturn(courses);

        // Ejecutar petición y verificar respuesta
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Java"))
                .andExpect(jsonPath("$[0].description").value("Java Course"));
    }

    @Test
    void whenGetCourseById_thenReturnsCourse() throws Exception {

        //Preparación de datos
        Course course = new Course();
        course.setId(1L);
        course.setName("Java");
        course.setDescription("Java Course");
        
        // Configurar Mock
        when(courseService.getCourseById(1L)).thenReturn(course);

        // Ejecutar petición y verificar respuesta
        mockMvc.perform(get("/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Java"))
                .andExpect(jsonPath("$.description").value("Java Course"));
    }

    @Test
    void whenAddCourse_thenReturnsCourse() throws Exception {

        //Preparación de datos
        Course course = new Course(); // Crear un objeto Course
        course.setId(1L); // Establecer el id
        course.setName("Java"); // Establecer el nombre
        course.setDescription("Java Course"); // Establecer la descripción
        
        // Configurar Mock
        when(courseService.addCourse(course)).thenReturn(course); // Configurar el servicio para que devuelva el objeto Course

        // Ejecutar petición y verificar respuesta
        mockMvc.perform(post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(course)))
                .andExpect(status().isOk());
    }

}
