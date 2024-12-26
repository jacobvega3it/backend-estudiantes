package com.tresit.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tresit.student.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
