package com.tresit.student.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tresit.student.dto.StudentDto;
import com.tresit.student.model.Career;
import com.tresit.student.model.Student;
import com.tresit.student.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final CareerService careerService;
    
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
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
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
