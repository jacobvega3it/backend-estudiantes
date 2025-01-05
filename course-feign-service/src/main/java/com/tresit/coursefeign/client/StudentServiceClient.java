package com.tresit.coursefeign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.tresit.commons.dto.CareerDto;

@FeignClient(name="student-service", url="${student-service.url}")
public interface StudentServiceClient {

    @GetMapping
    CareerDto[] findAll();

}
