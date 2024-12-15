package com.example.schoolsystem.OutDTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TeacherDTO {
    private String name;
    private String email;
    private List<CourseDTO> courseDTOS;
}
