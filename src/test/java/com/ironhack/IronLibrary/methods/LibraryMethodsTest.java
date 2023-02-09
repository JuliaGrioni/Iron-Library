package com.ironhack.IronLibrary.methods;

import com.ironhack.IronLibrary.model.Author;
import com.ironhack.IronLibrary.model.Book;
import com.ironhack.IronLibrary.model.Issue;
import com.ironhack.IronLibrary.model.Student;
import com.ironhack.IronLibrary.repository.AuthorRepository;
import com.ironhack.IronLibrary.repository.BookRepository;
import com.ironhack.IronLibrary.repository.IssueRepository;
import com.ironhack.IronLibrary.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class LibraryMethodsTest {

    private Book book1, book2;
    private Author author1, author2;
    private Student student1;
    private Issue issue;

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private IssueRepository issueRepository;
    @Autowired
    private StudentRepository studentRepository;
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

        student1 = new Student("123", "Teresa");

        SimpleDateFormat sdf = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy");
        Date date = new Date();
        String issueDate = sdf.format(date);
        Date returnDate = new Date(date.getTime() + (7 * 24 * 60 * 60 * 1000));
        String returnDateString = sdf.format(returnDate);
        issue = new Issue(issueDate, returnDateString, student1, book1);
    }

    @AfterEach
    void tearDown() {
        authorRepository.deleteAll();
        bookRepository.deleteAll();
        issueRepository.deleteAll();
        studentRepository.deleteAll();
    }

    @Test
    public void testAddBook_addNewBook_Works() {
        libraryMethods.addBook("123456789", "The Great Gatsby", "Novel","F. Scott Fitzgerald", "fscottfitzgerald@email.com", 10);
        Optional<Book> optionalBook = bookRepository.findBookByTitle("The Great Gatsby");
        assertTrue(optionalBook.isPresent());
        assertEquals("123456789", optionalBook.get().getIsbn());
        assertEquals("Novel", optionalBook.get().getCategory());
        assertEquals("The Great Gatsby", optionalBook.get().getTitle());
        assertEquals(10, optionalBook.get().getQuantity());
        assertEquals("F. Scott Fitzgerald", optionalBook.get().getAuthor().getName());
        assertEquals("fscottfitzgerald@email.com", optionalBook.get().getAuthor().getEmail());
    }

//    @Test
//    void searchBookByTitle_invalidTitle_NotFinds() {
//        assertThrows(IllegalArgumentException.class,() -> libraryMethods.searchBookByTitle("Galaxy"));
//    }

    @Test
    void searchBookByTitle_validTitle_Finds() {
        Optional<Book> optionalBook = libraryMethods.searchBookByTitle("The Hitchhiker's Guide to the Galaxy");
        assertTrue(optionalBook.isPresent());
        assertEquals(book2, optionalBook.get());
    }

//    @Test
//    void searchBookByCategory_invalidCategory_NotFinds() {
//        assertThrows(IllegalArgumentException.class,() -> libraryMethods.searchBookByCategory("89"));
//    }

    @Test
    void searchBookByCategory_validCategory_Finds() {
        List<Book> bookList = libraryMethods.searchBookByCategory("Science Fiction");
        assertNotNull(bookList);
        for (Book book : bookList) {
            assertEquals("Science Fiction", book.getCategory());
        }
    }

//    @Test
//    void searchBookByAuthor_invalidAuthor_NotFinds() {
//        assertThrows(IllegalArgumentException.class,() -> libraryMethods.searchBookByAuthor("Teresa"));
//    }

    @Test
    void searchBookByAuthor_validAuthor_Finds() {
        List<Book> bookList = libraryMethods.searchBookByAuthor(author1.getName());
        assertNotNull(bookList);
        for (Book book : bookList) {
            assertEquals(author1, book.getAuthor());
        }
    }

    @Test
    void listAllBooks() {
        List<Book> expectedBooks = Arrays.asList(book1,book2);
        List<Book> foundBooks = libraryMethods.listAllBooks();
        assertEquals(expectedBooks, foundBooks);
    }

    @Test
    @Transactional
    void issueBookToStudent_validInputs_Works() {
        libraryMethods.issueBookToStudent("123", "Teresa", "The Dark Tower");

        Optional<Student> optionalStudent = studentRepository.findByUsn("123");
        assertTrue(optionalStudent.isPresent());
        assertEquals("123", optionalStudent.get().getUsn());
        assertEquals("Teresa", optionalStudent.get().getName());

        List<Issue> optionalIssue = issueRepository.findByIssueStudentUsn("123");
        assertFalse(optionalIssue.isEmpty());
        Issue issue = optionalIssue.get(0);
        assertEquals("Teresa", issue.getIssueStudent().getName());
        assertEquals("The Dark Tower", issue.getIssueBook().getTitle());

        Optional<Book> updatedBook = bookRepository.findBookByTitle("The Dark Tower");
        assertTrue(updatedBook.isPresent());
        assertEquals(2, updatedBook.get().getQuantity());

    }

    @Test
    void listBooksByUsn_validUsn_Finds() {
        List<Issue> issueList = issueRepository.findByIssueStudentUsn("123");
        assertNotNull(issueList);
        for (Issue issue : issueList) {
            assertEquals("123", issue.getIssueStudent().getUsn());
        }
    }
}