package com.tresit.student.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.ArgumentMatchers.any;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.autoconfigure.RefreshAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tresit.commons.dto.student.StudentDto;
import com.tresit.student.model.Student;
import com.tresit.student.service.StudentService;

@WebMvcTest(StudentController.class)
@Import(RefreshAutoConfiguration.class)
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Test
    void whenGetAllStudents_thenReturnsStudentList() throws Exception {
        // Preparar datos de prueba
        Student student = new Student();//Declarar un objeto de tipo Student
        student.setStudentId(1L);//Asignar un valor al atributo studentId
        student.setName("Juan");//Asignar un valor al atributo name
        student.setSurname("Pérez");//Asignar un valor al atributo surname
        List<Student> students = Arrays.asList(student);//Crear una lista de estudiantes con un solo estudiante

        // Configurar mock para que retorne la lista de estudiantes cuando se llame al método getAllStudents
        when(studentService.getAllStudents()).thenReturn(students);

        // Ejecutar y verificar que se obtiene la lista de estudiantes
        mockMvc.perform(get("/estudiantes"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$[0].name").value("Juan"));
    }

    @Test
    void whenGetStudentById_thenReturnsStudent() throws Exception {
        // Preparar datos de prueba
        Student student = new Student();//Declarar un objeto de tipo Student
        student.setStudentId(1L);//Asignar un valor al atributo studentId
        student.setName("Juan");//Asignar un valor al atributo name

        // Configurar mock para que retorne un objeto de tipo Student cuando se llame al método getStudentById con el argumento 1L
        when(studentService.getStudentById(1L)).thenReturn(student);

        // Ejecutar y verificar que se obtiene el estudiante con el id 1
        mockMvc.perform(get("/estudiantes/1"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.name").value("Juan"));
    }

    @Test
    void whenAddStudent_thenReturnsCreatedStatus() throws Exception {
        // Preparar datos de prueba
        StudentDto studentDto = new StudentDto();//Declarar un objeto de tipo StudentDto
        studentDto.setName("Juan");//Asignar un valor al atributo name
        studentDto.setCareerId(1L);//Asignar un valor al atributo careerId

        Student savedStudent = new Student();//Declarar un objeto de tipo Student
        savedStudent.setStudentId(1L);//Asignar un valor al atributo studentId
        savedStudent.setName("Juan");//Asignar un valor al atributo name

        // Configurar mock para que retorne el estudiante guardado cuando se llame al método addStudent
        when(studentService.addStudent(any(StudentDto.class))).thenReturn(savedStudent);

        // Ejecutar y verificar que se crea el estudiante
        mockMvc.perform(post("/estudiantes/agregar")
               .contentType(MediaType.APPLICATION_JSON)
               .content(new ObjectMapper().writeValueAsString(studentDto)))
               .andExpect(status().isCreated());
    }    

}
