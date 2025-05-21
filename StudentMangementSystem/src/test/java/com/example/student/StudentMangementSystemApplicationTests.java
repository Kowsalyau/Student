package com.example.student;

import com.example.student.service.StudentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ApplicationStartupTests {

    @Autowired
    private StudentService studentService;

    @Test
    @DisplayName("Context should load without errors")
    void contextShouldLoad() {
        // Just ensures the Spring context loads
    }

    @Test
    @DisplayName("StudentService should be available in the application context")
    void studentServiceShouldBeInjected() {
        assertThat(studentService).isNotNull();
    }
}
