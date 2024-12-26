package com.tresit.student.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tresit.student.model.Career;
import com.tresit.student.repository.CareerRepository;

@Service
public class CareerServiceImpl implements CareerService {

    private final CareerRepository repository;
    
    public CareerServiceImpl(CareerRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Career> getAllCareers() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Career getCareerById(Long id) {
        return repository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Career not found. ID: " + id));
    }

    @Override
    @Transactional
    public Career addCareer(Career career) {
        return repository.save(career);
    }

    @Override
    @Transactional
    public void deleteCareer(Long id) {
        repository.deleteById(id);
    }

}
