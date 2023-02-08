package com.ironhack.IronLibrary.repository;

import com.ironhack.IronLibrary.model.Author;
import com.ironhack.IronLibrary.model.Book;
import com.ironhack.IronLibrary.model.Issue;
import com.ironhack.IronLibrary.model.Student;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class IssueRepositoryTest {

    private Issue issue1, issue2;
    private Book book;
    private Student student;

    @Autowired
    private IssueRepository issueRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;

    @BeforeEach
    void setUp() {
        book = new Book("012345678", "Fantasy", "The Dark Tower", 3, new Author("Stephen King", "stephenking@gmail.com"));
        SimpleDateFormat sdf = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy");
        Date date = new Date();
        String issueDate = sdf.format(date);
        Date returnDate = new Date(date.getTime() + (7 * 24 * 60 * 60 * 1000));
        String returnDateString = sdf.format(returnDate);

        student = new Student("123", "Teresa");

        issue1 = new Issue(issueDate, returnDateString, student, book);
        issue2 = new Issue(issueDate, returnDateString, student, book);

        student.setIssues(List.of(issue1, issue2));
        issueRepository.save(issue1);
        issueRepository.save(issue2);
    }

    @AfterEach
    void tearDown() {
        issueRepository.deleteAll();
        studentRepository.deleteAll();
        bookRepository.deleteAll();
        authorRepository.deleteAll();

    }

    @Test
    void findByIssueStudentUsn_invalidUsn_NotFind() {
        List<Issue> issueList = issueRepository.findByIssueStudentUsn("1");
        assertNotNull(issueList);
        assertTrue(issueList.isEmpty());
    }

    @Test
    void findByIssueStudentUsn_validUsn_Find() {
        List<Issue> issueList = issueRepository.findByIssueStudentUsn("123");
        assertNotNull(issueList);
        assertFalse(issueList.isEmpty());
        for (Issue issue : issueList) {
            assertEquals("123", issue.getIssueStudent().getUsn());
        }
    }
}