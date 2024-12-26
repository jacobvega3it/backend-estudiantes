package com.tresit.student.service;

import java.util.List;

import com.tresit.student.dto.StudentDto;
import com.tresit.student.model.Student;

public interface StudentService {

    public Student getStudentById(Long id);
    public List<Student> getAllStudents();
    public Student addStudent(StudentDto studentDto);
    public void deleteStudent(Long id);

}
