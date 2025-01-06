package com.tresit.student.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tresit.student.model.Career;
import com.tresit.student.model.Student;

@ExtendWith(MockitoExtension.class)
public class StudentRepositoryTest {

    @Mock
    private StudentRepository studentRepository;

    private Career career;
    private Student student;

    @BeforeEach
    void setUp() {
        // Crear una carrera de prueba
        career = new Career();// Declarar un objeto de tipo Career
        career.setCareerId(1L);// Asignar un valor al atributo careerId
        career.setName("Ingeniería Informática"); // Asignar un valor al atributo name
        career.setStudentList(new ArrayList<>()); // Crear una lista vacía de estudiantes

        // Crear un estudiante de prueba
        student = new Student(); // Declarar un objeto de tipo Student
        student.setStudentId(1L); // Asignar un valor al atributo studentId
        student.setName("Juan"); // Asignar un valor al atributo name
        student.setSurname("Pérez"); // Asignar un valor al atributo surname
        student.setCareer(career); // Asignar la carrera al estudiante

        // Actualizar la lista de estudiantes de la carrera
        career.getStudentList().add(student);
    }

    @Test
    void whenFindById_thenReturnStudent() {
        // Dado que el estudiante con id 1L existe en la base de datos
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        // Se busca el estudiante con id 1L
        Optional<Student> found = studentRepository.findById(1L);

        // Se verifica que el estudiante encontrado es el esperado
        assertTrue(found.isPresent()); // Verificar que se encontró un estudiante
        assertEquals(student.getName(), found.get().getName()); // Verificar que el nombre del estudiante es el esperado
        assertEquals(student.getSurname(), found.get().getSurname()); // Verificar que el apellido del estudiante es el esperado
        assertEquals(career.getCareerId(), found.get().getCareer().getCareerId()); // Verificar que la carrera del estudiante es la esperada
        verify(studentRepository, times(1)).findById(1L); // Verificar que se llamó al método findById con el argumento 1L
    }

    @Test
    void whenFindAll_thenReturnStudentList() {
        // Dado que hay un solo estudiante en la base de datos
        List<Student> studentList = List.of(student); // Crear una lista de estudiantes con un solo estudiante
        when(studentRepository.findAll()).thenReturn(studentList); // Configurar el mock para que retorne la lista de estudiantes

        // Se obtiene la lista de estudiantes
        List<Student> students = studentRepository.findAll();

        // Se verifica que la lista de estudiantes es la esperada
        assertNotNull(students); // Verificar que la lista de estudiantes no es nula
        assertEquals(1, students.size()); // Verificar que la lista tiene un solo elemento
        assertEquals(student.getName(), students.get(0).getName()); // Verificar que el nombre del estudiante es el esperado
        verify(studentRepository, times(1)).findAll(); // Verificar que se llamó al método findAll
    }

    @Test
    void whenSave_thenReturnSavedStudent() {
        // Datos de prueba
        Student newStudent = new Student(); // Declarar un objeto de tipo Student
        newStudent.setName("María"); // Asignar un valor al atributo name
        newStudent.setSurname("González"); // Asignar un valor al atributo surname
        newStudent.setCareer(career); // Asignar la carrera al estudiante

        // Configurar el mock para que retorne el estudiante guardado
        when(studentRepository.save(any(Student.class))).thenReturn(newStudent);   

        // Se guarda el estudiante
        Student saved = studentRepository.save(newStudent);

        // Se verifica que el estudiante guardado es el esperado
        assertNotNull(saved); // Verificar que el estudiante guardado no es nulo
        assertEquals(newStudent.getName(), saved.getName()); // Verificar que el nombre del estudiante es el esperado
        assertEquals(newStudent.getSurname(), saved.getSurname()); // Verificar que el apellido del estudiante es el esperado
        assertEquals(career.getCareerId(), saved.getCareer().getCareerId()); // Verificar que la carrera del estudiante es la esperada
        verify(studentRepository, times(1)).save(any(Student.class)); // Verificar que se llamó al método save con cualquier objeto de tipo Student como argumento
    }

    @Test
    void whenDeleteById_thenVerifyDeletion() {
        // Se elimina el estudiante con id 1L
        studentRepository.deleteById(1L); 

        // Se verifica que se eliminó el estudiante con id 1L
        verify(studentRepository, times(1)).deleteById(1L);
    }
    
    @Test
    void whenFindById_thenReturnEmpty() {
        // Configurar el mock para que retorne un Optional vacío
        when(studentRepository.findById(999L)).thenReturn(Optional.empty()); 

        // Se busca el estudiante con id 999L
        Optional<Student> found = studentRepository.findById(999L); 

        // Verificar que no se encontró un estudiante
        assertFalse(found.isPresent()); 
        // Verificar que se llamó al método findById con el argumento 999L
        verify(studentRepository, times(1)).findById(999L); 
    }
}
