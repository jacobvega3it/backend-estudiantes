package com.tresit.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tresit.student.model.Career;

public interface CareerRepository extends JpaRepository<Career, Long> {

}
