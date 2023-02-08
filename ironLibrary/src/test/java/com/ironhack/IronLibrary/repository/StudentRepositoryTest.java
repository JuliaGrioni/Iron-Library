package com.ironhack.IronLibrary.repository;

import com.ironhack.IronLibrary.model.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class StudentRepositoryTest {

    private Student student1, student2;
    @Autowired
    private StudentRepository studentRepository;

    @BeforeEach
    void setUp() {
        student1 = new Student("123", "Teresa");
        student2 = new Student("456", "Manuela");
        studentRepository.save(student1);
        studentRepository.save(student2);
    }

    @AfterEach
    void tearDown() {
        studentRepository.deleteAll();
    }

    @Test
    void findByUsn_invalidUsn_NotFind() {
        Optional<Student> optionalStudent = studentRepository.findByUsn("000");
        assertFalse(optionalStudent.isPresent());
    }
    @Test
    void findByUsn_validUsn_Find() {
        Optional<Student> optionalStudent = studentRepository.findByUsn("123");
        assertTrue(optionalStudent.isPresent());
        assertEquals(student1, optionalStudent.get());
    }
}