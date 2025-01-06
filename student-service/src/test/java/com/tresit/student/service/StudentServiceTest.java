package com.tresit.student.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tresit.commons.dto.student.StudentDto;
import com.tresit.student.model.Career;
import com.tresit.student.model.Student;
import com.tresit.student.repository.StudentRepository;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private CareerService careerService;

    @InjectMocks
    private StudentServiceImpl studentService;

    @Test
    void whenGetAllStudents_thenReturnsStudentList() {
        // Preparar datos de prueba
        Student student = new Student(); //Declarar un objeto de tipo Student
        student.setName("Juan");//Asignar un valor al atributo name
        List<Student> students = Arrays.asList(student);//Crear una lista de estudiantes con un solo estudiante

        // Configurar mock para que retorne la lista de estudiantes cuando se llame al método findAll
        when(studentRepository.findAll()).thenReturn(students);

        // Ejecutar el método getAllStudents
        List<Student> result = studentService.getAllStudents();

        // Verificar que el resultado es el esperado
        assertEquals(1, result.size());//Verificar que la lista tiene un solo elemento
        assertEquals("Juan", result.get(0).getName());//Verificar que el nombre del estudiante es "Juan"
    }

    @Test
    void whenGetStudentById_thenReturnStudent() {
        // Preparar datos de prueba
        Student student = new Student();//Declarar un objeto de tipo Student
        student.setStudentId(1L);//Asignar un valor al atributo studentId
        student.setName("Juan");//Asignar un valor al atributo name

        //Configurar el mock para que retorne un objeto de tipo Student cuando se llame al método findById con el argumento 1L
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        // Ejecutar el método getStudentById con el argumento 1L
        Student result = studentService.getStudentById(1L);

        // Verificar que el resultado es el esperado
        assertEquals("Juan", result.getName());
    }

    @Test
    void whenAddStudent_thenReturnSavedStudent() {
        // Preparar datos de prueba
        StudentDto studentDto = new StudentDto();//Declarar un objeto de tipo StudentDto
        studentDto.setName("Juan");//Asignar un valor al atributo name
        studentDto.setCareerId(1L);//Asignar un valor al atributo careerId

        Career career = new Career();//Declarar un objeto de tipo Career
        career.setCareerId(1L);//Asignar un valor al atributo careerId

        Student savedStudent = new Student();//Declarar un objeto de tipo Student
        savedStudent.setName("Juan");//Asignar un valor al atributo name

        //Configurar el mock para que retorne un objeto de tipo Career cuando se llame al método getCareerById con el argumento 1L 
        when(careerService.getCareerById(1L)).thenReturn(career);
        //Configurar el mock para que retorne un objeto de tipo Student cuando se llame al método save con cualquier objeto de tipo Student como argumento
        when(studentRepository.save(any(Student.class))).thenReturn(savedStudent);

        // Ejecutar el método addStudent con el objeto studentDto como argumento
        Student result = studentService.addStudent(studentDto);

        // Verificar que el resultado es el esperado
        assertEquals("Juan", result.getName());
    }

}
