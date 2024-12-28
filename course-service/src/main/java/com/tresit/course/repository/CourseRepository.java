package com.tresit.course.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tresit.course.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

    Optional<Course> findByName(String name);

}
