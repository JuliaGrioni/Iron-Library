package com.ironhack.IronLibrary.repository;

import com.ironhack.IronLibrary.methods.LibraryMethods;
import com.ironhack.IronLibrary.model.Author;
import com.ironhack.IronLibrary.model.Book;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class BookRepositoryTest {

    private Book book1, book2;
    private Author author1, author2;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private LibraryMethods libraryMethods;

    @BeforeEach
    void setUp() {
        author1 = new Author("Stephen King", "stephenking@gmail.com");
        book1 = new Book("012345678", "Fantasy", "The Dark Tower", 3, author1);
        authorRepository.save(author1);
        bookRepository.save(book1);

        author2 = new Author("Douglas Adams", "douglasadams@gmail.com");
        book2 = new Book("123456780", "Science Fiction", "The Hitchhiker's Guide to the Galaxy", 5, author2);
        authorRepository.save(author2);
        bookRepository.save(book2);
    }


    @AfterEach
    void tearDown() {
        authorRepository.deleteAll();
        bookRepository.deleteAll();
    }

    @Test
    void findBookByTitle_invalidTitle_NotFind() {
        Optional<Book> optionalBook = bookRepository.findBookByTitle("Galaxy");
        assertFalse(optionalBook.isPresent());
    }

    @Test
    void findBookByTitle_validTitle_Find() {
        Optional<Book> optionalBook = bookRepository.findBookByTitle("The Hitchhiker's Guide to the Galaxy");
        assertTrue(optionalBook.isPresent());
        assertEquals(book2, optionalBook.get());
    }

    @Test
    void findBookByCategory_invalidCategory_NotFind() {
        List<Book> bookList = bookRepository.findBookByCategory("56");
        assertTrue(bookList.isEmpty());
    }

    @Test
    void findBookByCategory_validCategory_Find() {
        List<Book> bookList = bookRepository.findBookByCategory("Science Fiction");
        assertNotNull(bookList);
        for (Book book : bookList) {
            assertEquals("Science Fiction", book.getCategory());
        }
    }

    @Test
    void findBookByAuthor_validAuthor_Find() {
        List<Book> bookList = bookRepository.findBookByAuthor(author1);
        assertNotNull(bookList);
        for (Book book : bookList) {
            assertEquals(author1, book.getAuthor());
        }
    }
}