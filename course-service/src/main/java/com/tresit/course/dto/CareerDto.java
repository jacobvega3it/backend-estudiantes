package com.tresit.course.dto;

import java.util.List;

public class CareerDto {

    private Long careerId;
    private String name;
    private List<StudentDto> studentList;
    
    public Long getCareerId() {
        return careerId;
    }
    public void setCareerId(Long careerId) {
        this.careerId = careerId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<StudentDto> getStudentList() {
        return studentList;
    }
    public void setStudentList(List<StudentDto> studentList) {
        this.studentList = studentList;
    }

    

}
