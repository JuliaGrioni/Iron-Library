package com.ironhack.IronLibrary.repository;

import com.ironhack.IronLibrary.model.Author;
import com.ironhack.IronLibrary.model.Book;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BookRepositoryTest {

    private Book book1, book2;

    @Autowired
    BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        book1 = new Book("123456789", "Novel", "The Great Gatsby", 10, new Author("F. Scott Fitzgerald", "fscottfitzgerald@email.com"));
        book2 = new Book("1234567890", "Science Fiction", "The Hitchhiker's Guide to the Galaxy", 5, new Author("Douglas Adams", "douglasadams@gmail.com"));
    }

    @AfterEach
    void tearDown() {
        bookRepository.deleteAll();
    }


    @Test
    public void testAddBook() {
        bookRepository.save(book1);
        bookRepository.save(book2);
        Optional<Book> optionalBook = bookRepository.searchBookByTitle("The Great Gatsby");
        assertTrue(optionalBook.isPresent());
        Book savedBook = optionalBook.get();
        assertEquals("123456789", savedBook.getIsbn());
        assertEquals("Novel", savedBook.getCategory());
        assertEquals("The Great Gatsby", savedBook.getTitle());
        assertEquals(10, savedBook.getQuantity());
        assertEquals("F. Scott Fitzgerald", savedBook.getAuthor().getName());
        assertEquals("fscottfitzgerald@email.com", savedBook.getAuthor().getEmail());
    }

    @Test
    public void testSearchBookByTitle() {
        Book book = new Book("1234567890", "Science Fiction", "The Hitchhiker's Guide to the Galaxy", 5, new Author("Douglas Adams", "douglasadams@gmail.com"));
        bookRepository.save(book);

        String title = "The Hitchhiker's Guide to the Galaxy";
        Optional<Book> optionalBook = bookRepository.searchBookByTitle(title);
        assertTrue(optionalBook.isPresent());
        assertEquals(book, optionalBook.get());
    }

    @Test
    public void testSearchBookByCategory() {
        List<Book> bookList = bookRepository.searchBookByCategory("Science Fiction");
        assertNotNull(bookList);
        assertFalse(bookList.isEmpty());
        for (Book book : bookList) {
            assertEquals("Science Fiction", book.getCategory());
        }
    }
}