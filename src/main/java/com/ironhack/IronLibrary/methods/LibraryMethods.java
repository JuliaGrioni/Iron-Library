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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class LibraryMethods {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private IssueRepository issueRepository;
    @Autowired
    private StudentRepository studentRepository;


    public void addBook(String isbn, String title, String category, String name, String email, int quantity){
        Author author;
        try {
            Optional<Author> optionalAuthor = authorRepository.findByName(name);
            if (!optionalAuthor.isPresent()) {
                author = new Author(name, email);
                authorRepository.save(author);
            } else {
                author = optionalAuthor.get();
            }
            Book book1 = new Book(isbn, category, title, quantity, author);
            bookRepository.save(book1);
            System.out.println("Book and Author added successfully!");
        } catch (Exception e){
            System.err.println("Something went wrong: " + e.getMessage());
        }
    }


    public Optional<Book> searchBookByTitle(String title){
        Optional<Book> optionalBook = bookRepository.findBookByTitle(title);
       if(optionalBook.isPresent()){
           System.out.printf(String.format("%-20s%-30s%-20s%-20s\n","Book ISBN","Book Title","Category","No of Books"));
           System.out.printf(String.format("%-20s%-30s%-20s%-20s", optionalBook.get().getIsbn(), optionalBook.get().getTitle(), optionalBook.get().getCategory(), optionalBook.get().getQuantity()));
       }
       else {
           System.err.println("No books found with the given title.");
      //     throw new IllegalArgumentException("No books found with the given title.");
       }
        return optionalBook;
    }


    public List<Book> searchBookByCategory(String category) {
        List<Book> bookList = bookRepository.findBookByCategory(category);
        if (bookList.isEmpty()) {
            System.err.println("No books found with the given category.");
//            throw new IllegalArgumentException("No books found with the given category.");
        } else {
            System.out.println("Books found with the given category:");
            System.out.printf(String.format("%-20s%-30s%-20s%-20s\n","Book ISBN","Book Title","Category","No of Books"));
            for (Book book : bookList) {
                System.out.println(String.format("%-20s%-30s%-20s%-20s", book.getIsbn(), book.getTitle(), book.getCategory(), book.getQuantity()));
            }
        }
        return bookList;
    }


    public List<Book> searchBookByAuthor(String authorName) {
        Optional<Author> foundAuthor = authorRepository.findByName(authorName);
        List<Book> bookList = null;
        if (!foundAuthor.isPresent()) {
            System.err.println("No authors found with the given name.");
//            throw new IllegalArgumentException("No authors found with the given name.");
        } else {
            bookList = bookRepository.findBookByAuthor(foundAuthor.get());
            if (bookList.isEmpty()) {
                System.err.println("No authors found with the given name.");
//                throw new IllegalArgumentException("No authors found with the given name.");
            } else {
                System.out.println("Books written by the author:");
                System.out.printf(String.format("%-20s%-30s%-20s%-20s\n", "Book ISBN", "Book Title", "Category", "No of Books"));
                for (Book book : bookList) {
                    System.out.println(String.format("%-20s%-30s%-20s%-20s", book.getIsbn(), book.getTitle(), book.getCategory(), book.getQuantity()));
                }
            }
        }
        return bookList;
    }

    public List<Book> listAllBooks() {
        List<Book> bookList = bookRepository.findAll();
        if (bookList.isEmpty()) {
            System.err.println("No books found in the library.");
//            throw new IllegalArgumentException("No books found in the library.");
        } else {
            System.out.println("List of all books with corresponding authors:");
            System.out.printf(String.format("%-20s%-30s%-20s%-20s%-20s%-20s\n","Book ISBN","Book Title","Category","No of Books","Author name","Author mail"));
            for (Book book : bookList) {
                Author author = book.getAuthor();
                System.out.println(String.format("%-20s%-30s%-20s%-20s%-20s%-20s", book.getIsbn(), book.getTitle(), book.getCategory(), book.getQuantity(), author.getName(), author.getEmail()));
            }
        }
        return bookList;
    }

    @Transactional
    public void issueBookToStudent(String usn, String name,  String title) {
        Optional<Book> book = bookRepository.findBookByTitle(title);
        if (!book.isPresent()) {
 //           throw new IllegalArgumentException("Book not found in the library");
            System.err.println("Book not found in the library");
        } else if (book.get().getQuantity() == 0) {
//            throw new IllegalArgumentException("Book is not available");
            System.err.println("Book is not available");
        } else {
            Optional<Student> optionalStudent = studentRepository.findByUsn(usn);
            Student student;
            if (!optionalStudent.isPresent()) {
                student = new Student(usn, name);
                student.setIssues(new ArrayList<>());

            } else {
                student = optionalStudent.get();
            }
                book.get().setQuantity(book.get().getQuantity() - 1);

                SimpleDateFormat sdf = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy");
                Date date = new Date();
                String issueDate = sdf.format(date);
                Date returnDate = new Date(date.getTime() + (7 * 24 * 60 * 60 * 1000));
                String returnDateString = sdf.format(returnDate);
                Issue issue = new Issue(issueDate, returnDateString, student, book.get());
                student.getIssues().add(issue);
                issueRepository.save(issue);
                System.out.println("Book issued. Return date: " + returnDateString);

        }
    }


    public void listBooksByUsn(String usn) {
        List<Issue> issueList = issueRepository.findByIssueStudentUsn(usn);
        if(issueList.isEmpty()) {
            System.err.println("No books rented by the student with USN: " + usn);
        } else {
            System.out.println("Books rented by the student with USN: " + usn);
            System.out.printf(String.format("%-30s%-20s%-20s\n", "Book Title", "Student Name", "Return Date"));
            for(Issue issue : issueList) {
                System.out.println(String.format("%-30s%-20s%-20s", issue.getIssueBook().getTitle(), issue.getIssueStudent().getName(), issue.getReturnDate()));
            }
        }
    }

}
