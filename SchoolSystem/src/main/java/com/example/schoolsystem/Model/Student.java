package com.example.schoolsystem.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "varchar(21) not null")
    @NotEmpty(message = "Student Name cannot be empty")
    private String name;

    @Column(columnDefinition = "int not null")
    @NotNull(message = "Student Age cannot be empty")
    private Integer age;

    @Column(columnDefinition = "varchar(21) not null")
    @NotEmpty(message = "Student Major cannot be empty")
    private String major;

    @ManyToMany
    @JsonIgnore
    private Set<Course> courses;
}
