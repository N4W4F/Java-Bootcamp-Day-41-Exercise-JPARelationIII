package com.example.schoolsystem.Service;

import com.example.schoolsystem.ApiResponse.ApiException;
import com.example.schoolsystem.Model.Course;
import com.example.schoolsystem.Model.Student;
import com.example.schoolsystem.OutDTO.CourseDTO;
import com.example.schoolsystem.OutDTO.StudentDTO;
import com.example.schoolsystem.Repository.CourseRepository;
import com.example.schoolsystem.Repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public List<StudentDTO> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        List<StudentDTO> studentDTOS = new ArrayList<>();

        for (Student s : students) {
            List<CourseDTO> courseDTOS = new ArrayList<>();
            for (Course c : s.getCourses())
                courseDTOS.add(new CourseDTO(c.getName()));

            studentDTOS.add(new StudentDTO(s.getName(), s.getMajor(), courseDTOS));
        }
        return studentDTOS;
    }

    public void addStudent(Student student) {
        studentRepository.save(student);
    }

    public void updateStudent(Integer id, Student student) {
        Student oldStudent = studentRepository.findStudentById(id);
        if (oldStudent == null)
            throw new ApiException("Student with ID: " + id + " was not found");

        oldStudent.setName(student.getName());
        oldStudent.setAge(student.getAge());
        oldStudent.setMajor(student.getMajor());
        studentRepository.save(oldStudent);
    }

    public void deleteStudent(Integer id) {
        Student student = studentRepository.findStudentById(id);
        if (student == null)
            throw new ApiException("Student with ID: " + id + " was not found");

        studentRepository.delete(student);
    }

    public void changeMajor(Integer id, String major) {
        Student student = studentRepository.findStudentById(id);
        if (student == null)
            throw new ApiException("Student with ID: " + id + " was not found");

        student.setMajor(major);
        student.setCourses(null);
        studentRepository.save(student);
    }

    public List<StudentDTO> getStudentByCourseId(Integer courseId) {
        Course course = courseRepository.findCourseById(courseId);
        if (course == null)
            throw new ApiException("Course with ID: " + courseId + " was not found");

        List<StudentDTO> studentDTOS = new ArrayList<>();

        for (Student s : course.getStudents()) {
            studentDTOS.add(new StudentDTO(s.getName(), s.getMajor()));
        }
        return studentDTOS;
    }

//    public List<Student> getStudentByCourseId(Integer courseId) {
//        Course course = courseRepository.findCourseById(courseId);
//        if (course == null)
//            throw new ApiException("Course with ID: " + courseId + " was not found");
//
//        List<Student> students = new ArrayList<>();
//
//        for (Student s : studentRepository.findAll())
//            if (s.getCourses().contains(course))
//                students.add(s);
//
//        return students;
//    }

    public void enrollStudentToCourse(Integer studentId, Integer courseId) {
        Student student = studentRepository.findStudentById(studentId);
        if (student == null)
            throw new ApiException("Student with ID: " + studentId + " was not found");

        Course course = courseRepository.findCourseById(courseId);
        if (course == null)
            throw new ApiException("Course with ID: " + courseId + " was not found");

        student.getCourses().add(course);
        studentRepository.save(student);

        course.getStudents().add(student);
        courseRepository.save(course);
    }

    public Student getStudentById(Integer id) {
        Student student = studentRepository.findStudentById(id);
        if (student == null)
            throw new ApiException("Student with ID: " + id + " was not found");

        return student;
    }
}
