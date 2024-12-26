package com.tresit.student.service;

import java.util.List;

import com.tresit.student.model.Career;

public interface CareerService {

    public List<Career> getAllCareers();
    public Career getCareerById(Long id);
    public Career addCareer(Career career);
    public void deleteCareer(Long id);

}
