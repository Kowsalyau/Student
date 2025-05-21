package com.example.student.controller;

import com.example.student.response.ApiResponse;
import com.example.student.model.Student;
import com.example.student.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students") // Slightly changed base URL
public class StudentController {

    private final StudentService studentService;

    // Constructor injection (preferred)
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Student>> fetchStudentById(@PathVariable Long id) {
        Student student = studentService.getStudentById(id);
        if (student != null) {
            return ResponseEntity.ok(
                    new ApiResponse<>("success", "Student retrieved", student)
            );
        }
        return new ResponseEntity<>(
                new ApiResponse<>("error", "Student not found", null),
                HttpStatus.NOT_FOUND
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Student>> modifyStudent(
            @PathVariable Long id,
            @RequestBody Student updatedData) {
        try {
            Student updatedStudent = studentService.updateStudent(id, updatedData);
            return ResponseEntity.ok(
                    new ApiResponse<>("success", "Student updated", updatedStudent)
            );
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(
                    new ApiResponse<>("error", "Student not found", null),
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> removeStudent(@PathVariable Long id) {
        try {
            studentService.deleteStudent(id);
            return ResponseEntity.ok(
                    new ApiResponse<>("success", "Student deleted", null)
            );
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(
                    new ApiResponse<>("error", "Student not found", null),
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Student>>> fetchAllStudents() {
        List<Student> allStudents = studentService.getAllStudents();
        return ResponseEntity.ok(
                new ApiResponse<>("success", "Fetched all students", allStudents)
        );
    }
}
