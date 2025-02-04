package com.example.brief17;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.brief17.entity.Student;
import com.example.brief17.service.StudentService;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        properties = {
        "spring.datasource.url=jdbc:h2:mem:testdb",
        "spring.jpa.hibernate.ddl-auto=create-drop"
        }
        )
@ActiveProfiles("test")
class StudentServiceIntegrationTest {
    @Autowired
    private StudentService studentService;

    @Test
    void shouldSaveAndRetrieveStudent() throws InterruptedException {
        Student testStudent = new Student();
        testStudent.setName("John");
        testStudent.setAddress("12 rue de la Paix");
        studentService.saveStudent(testStudent);

        // Délai
        Thread.sleep(40000);

        Optional<Student> retrievedStudent = studentService.findStudentById(testStudent.getId());
        assertThat(retrievedStudent).isPresent();
        assertThat(retrievedStudent.get().getName()).isEqualTo("John");
    }

    @Test
    void shouldFindStudentById() throws InterruptedException {
        Student savedStudent = new Student();
        savedStudent.setName("John");
        savedStudent.setAddress("12 rue de la Paix");
        Student returnedStudent = studentService.saveStudent(savedStudent);

        // Délai
        Thread.sleep(40000);

        Optional<Student> foundStudent = studentService.findStudentById(returnedStudent.getId());
        assertThat(foundStudent).isPresent();
        assertThat(foundStudent.get().getName()).isEqualTo("John");
    }
}
