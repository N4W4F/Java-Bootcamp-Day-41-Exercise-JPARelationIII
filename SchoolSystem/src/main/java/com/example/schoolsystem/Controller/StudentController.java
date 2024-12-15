package com.example.schoolsystem.Controller;

import com.example.schoolsystem.ApiResponse.ApiResponse;
import com.example.schoolsystem.Model.Student;
import com.example.schoolsystem.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/students")
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/get")
    public ResponseEntity getAllStudents() {
        return ResponseEntity.status(200).body(studentService.getAllStudents());
    }

    @PostMapping("/add")
    public ResponseEntity addStudent(@RequestBody @Valid Student student) {
        studentService.addStudent(student);
        return ResponseEntity.status(200).body(new ApiResponse("Student has been added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateStudent(@PathVariable Integer id, @RequestBody @Valid Student student) {
        studentService.updateStudent(id, student);
        return ResponseEntity.status(200).body(new ApiResponse("Student with ID: " + id + " has been updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteStudent(@PathVariable Integer id) {
        studentService.deleteStudent(id);
        return ResponseEntity.status(200).body(new ApiResponse("Student with ID: " + id + " has been updated successfully"));
    }

    @PutMapping("/change-major/{id}/{major}")
    public ResponseEntity changeMajor(@PathVariable Integer id, @PathVariable String major) {
        studentService.changeMajor(id, major);
        return ResponseEntity.status(200).body(new ApiResponse("Student Major has been changed successfully"));
    }

    @GetMapping("/get/by-course/{courseId}")
    public ResponseEntity getStudentByCourseId(@PathVariable Integer courseId) {
        return ResponseEntity.status(200).body(studentService.getStudentByCourseId(courseId));
    }

    @PutMapping("/enroll/{studentId}/{courseId}")
    public ResponseEntity enrollStudentToCourse(@PathVariable Integer studentId, @PathVariable Integer courseId) {
        studentService.enrollStudentToCourse(studentId, courseId);
        return ResponseEntity.status(200).body(new ApiResponse("Student with ID: " + studentId + " has been enrolled to course with ID: " + courseId));
    }

    @GetMapping("/get/by-id/{id}")
    public ResponseEntity getStudentById(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(studentService.getStudentById(id));
    }
}
