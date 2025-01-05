package com.tresit.student.service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tresit.student.dto.StudentDto;
import com.tresit.student.model.Career;
import com.tresit.student.model.Student;
import com.tresit.student.repository.StudentRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final CareerService careerService;
    private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);
    
    public StudentServiceImpl(StudentRepository studentRepository, CareerService careerService) {
        this.studentRepository = studentRepository;
        this.careerService = careerService;
    }

    @Override
    @Transactional(readOnly = true)
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Student not found. ID: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    @CircuitBreaker(name = "studentService", fallbackMethod = "getAllStudentsFallback")
    public List<Student> getAllStudents() {
        logger.info("Intento de obtener estudiantes - " + LocalDateTime.now());
        if (Math.random() < 0.7) {
            logger.error("Error simulado al obtener estudiantes - ");
            throw new RuntimeException("Error simulado en el servicio");
        }
        return studentRepository.findAll();
    }

    public List<Student> getAllStudentsFallback(Exception e) {
        logger.warn("Fallback activado - " + LocalDateTime.now());
        logger.warn("Causa del fallback: " + e.getMessage());

        Student fallbackStudent = new Student();
        fallbackStudent.setStudentId(999L);
        fallbackStudent.setName("Fallback");
        fallbackStudent.setSurname("Student");
        return Arrays.asList(null, fallbackStudent);
    }

    @Override
    @Transactional
    public Student addStudent(StudentDto studentDto) {
        Long careerId = studentDto.getCareerId();
        Career career = careerService.getCareerById(careerId);
        Student student = new Student();
        student.setName(studentDto.getName());
        student.setSurname(studentDto.getSurname());
        student.setCareer(career);
        return studentRepository.save(student);
    }

    @Override
    @Transactional
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

}
