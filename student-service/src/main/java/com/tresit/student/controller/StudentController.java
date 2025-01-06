package com.tresit.student.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;
//import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tresit.commons.dto.student.StudentDto;
import com.tresit.student.model.Student;
import com.tresit.student.service.StudentService;
import com.tresit.student.service.StudentServiceImpl;

//import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
//import io.github.resilience4j.timelimiter.annotation.TimeLimiter;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RefreshScope
@RestController
@RequestMapping("/estudiantes")
public class StudentController {

    @Autowired
    private StudentService service;

    private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = service.getAllStudents();
        if (students.isEmpty()) {
            return ResponseEntity.noContent().build();

        }
        return ResponseEntity.ok(students);
    }

    //@CircuitBreaker(name = "studentService", fallbackMethod = "getOneStudentFallback")
    //@TimeLimiter(name = "studentService")
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {

        Student student = service.getStudentById(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);

        /*return CompletableFuture.supplyAsync(() -> {

            if (id.equals(3L)){
                throw new IllegalStateException("Error simulado en el controlador");
            }
            if (id.equals(7L)){
                try {
                    TimeUnit.SECONDS.sleep(2L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
    
    
        });*/

    }

    @PostMapping("/agregar")
    public ResponseEntity<Student> addStudent(@RequestBody StudentDto studentDto) {
        Student createdStudent = service.addStudent(studentDto);
        return ResponseEntity.status(201).body(createdStudent);
    }    

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        service.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    public CompletableFuture<?> getOneStudentFallback(Exception e) {

        return CompletableFuture.supplyAsync(() -> {

            logger.warn("Fallback activado - " + LocalDateTime.now());
            logger.warn("Causa del fallback: " + e.getMessage());
    
            Student fallbackStudent = new Student();
            fallbackStudent.setStudentId(888L);
            fallbackStudent.setName("Fallback");
            fallbackStudent.setSurname("Timelimiter Student");
            return fallbackStudent;
    
        });

    }

}
