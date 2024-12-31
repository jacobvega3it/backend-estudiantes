package com.tresit.coursefeign.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tresit.coursefeign.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

    Optional<Course> findByName(String name);

}
