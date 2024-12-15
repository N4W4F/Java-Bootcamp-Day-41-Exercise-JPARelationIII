package com.example.schoolsystem.OutDTO;

import com.example.schoolsystem.Model.Course;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class StudentDTO {
    private String name;
    private String major;
    private List<CourseDTO> courses;

    public StudentDTO(String name, String major) {
        this.name = name;
        this.major = major;
    }
}
