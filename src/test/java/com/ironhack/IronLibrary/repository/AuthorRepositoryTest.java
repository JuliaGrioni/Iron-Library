package com.ironhack.IronLibrary.repository;

import com.ironhack.IronLibrary.methods.LibraryMethods;
import com.ironhack.IronLibrary.model.Author;
import com.ironhack.IronLibrary.model.Book;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AuthorRepositoryTest {
    private Author author;

    @Autowired
    private AuthorRepository authorRepository;


    @BeforeEach
    void setUp() {
        author = new Author("Douglas Adams", "douglasadams@gmail.com");
        authorRepository.save(author);
    }

    @AfterEach
    void tearDown() {
        authorRepository.deleteAll();
    }

    @Test
    void findByName_nameNotExists_NotFind() {
        Optional<Author> optionalAuthor = authorRepository.findByName("Teresa");
        assertFalse(optionalAuthor.isPresent());
    }

    @Test
    void findByName_nameExists_Find() {
        Optional<Author> optionalAuthor = authorRepository.findByName("Douglas Adams");
        assertTrue(optionalAuthor.isPresent());
        assertEquals(author, optionalAuthor.get());
    }
}