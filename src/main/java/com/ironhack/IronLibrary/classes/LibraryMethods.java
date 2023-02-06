package com.ironhack.IronLibrary.classes;
import com.ironhack.IronLibrary.model.Author;
import com.ironhack.IronLibrary.model.Book;
import com.ironhack.IronLibrary.model.Issue;
import com.ironhack.IronLibrary.model.Student;
import com.ironhack.IronLibrary.repository.AuthorRepository;
import com.ironhack.IronLibrary.repository.BookRepository;
import com.ironhack.IronLibrary.repository.IssueRepository;
import com.ironhack.IronLibrary.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
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

    public void addBook(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter isbn: ");
        String isbn = scanner.nextLine();
        System.out.println("Enter title: ");
        String title = scanner.nextLine();
        System.out.println("Enter category: ");
        String category= scanner.nextLine();
        System.out.println("Enter author name: ");
        String name = scanner.nextLine();
        System.out.println("Enter author mail: ");
        String email = scanner.nextLine();
        System.out.println("Enter number of books: ");
        int quantity = scanner.nextInt();

        Author author;
        Optional<Author> optionalAuthor = authorRepository.findByName(name);
        if(!optionalAuthor.isPresent()){
            author = new Author(name, email);
            authorRepository.save(author);
        }else{
            author = optionalAuthor.get();
        }


        Book book1= new Book(isbn, category,title, quantity, author);
        bookRepository.save(book1);
        System.out.println("Book and Author added successfully!");

    }

    public void searchBookByTitle(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write the book's title: ");
        String title= scanner.nextLine();
        Optional<Book> optionalBook = bookRepository.searchBookByTitle(title);
       if(optionalBook.isPresent()){
           System.out.println(optionalBook.get());
       }
       else {
           System.out.println("Book ISBN\tBook Title\tCategory\tNo of Books");
           System.out.println(optionalBook.get().getIsbn() + "\t\t" + optionalBook.get().getTitle() + "\t\t" + optionalBook.get().getCategory() + "\t\t" + optionalBook.get().getQuantity());
       }
    }

    public void searchBookByCategory() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write the book's category: ");
        String category = scanner.nextLine();
        List<Book> bookList = bookRepository.searchBookByCategory(category);
        if (bookList.isEmpty()) {
            System.out.println("No books found with the given category.");
        } else {
            System.out.println("Books found with the given category:");
            System.out.println("Book ISBN\tBook Title\tCategory\tNo of Books");
            for (Book book : bookList) {
                System.out.println(book.getIsbn() + "\t\t" + book.getTitle() + "\t\t" + book.getCategory() + "\t\t" + book.getQuantity());
            }
        }
    }

    public void searchBookByAuthor() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter author name: ");
        String name = scanner.nextLine();
        Optional<Author> author = authorRepository.findByName(name);
        if (!author.isPresent()) {
            System.out.println("No authors found with the given name.");
        } else {
            System.out.println("Books written by the author:");
            System.out.println("Book ISBN\tBook Title\tCategory\tNo of Books");
            List<Book> books = author.get().getBooks();
            for (Book book : books) {
                System.out.println(book.getIsbn() + "\t\t" + book.getTitle() + "\t\t" + book.getCategory() + "\t\t" + book.getQuantity());
            }
        }
    }

    public void listAllBooks() {
        List<Book> books = bookRepository.findAll();
        if (books.isEmpty()) {
            System.out.println("No books found in the library.");
        } else {
            System.out.println("List of all books with corresponding authors:");
            System.out.println("Book ISBN\t\tBook Title\tCategory\tNo of Books\tAuthor name\tAuthor mail");
            for (Book book : books) {
                System.out.print(book.getIsbn() + "\t\t");
                System.out.print(book.getTitle() + "\t\t");
                System.out.print(book.getCategory() + "\t\t");
                System.out.print(book.getQuantity() + "\t\t");
                Author author = book.getAuthor();
                System.out.print(author.getName() + "\t\t");
                System.out.println(author.getEmail());
            }
        }
    }

    public void issueBookToStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter student USN: ");
        String usn = scanner.nextLine();
        System.out.println("Enter student name: ");
        String name = scanner.nextLine();
        System.out.println("Enter book title: ");
        String title = scanner.nextLine();
        // Check if the book is available in the library
        Optional<Book> book = bookRepository.searchBookByTitle(title);
        if (book == null) {
            System.out.println("Book not found in the library");
        } else if (book.get().getQuantity() == 0) {
            System.out.println("Book is not available in the library");
        } else {
            // Check if the student already exists
            Student student = studentRepository.findByUsn(usn);
            if (student == null) {
                // Create the student if not exists
                student = new Student(usn, name);
                studentRepository.save(student);
            }
            // Decrease the quantity of the book
            book.get().setQuantity(book.get().getQuantity() - 1);
//            bookRepository.save(book);

            // Create the issue record
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Date date = new Date();
            String issueDate = sdf.format(date);
            Date returnDate = new Date(date.getTime() + (7 * 24 * 60 * 60 * 1000));
            String returnDateString = sdf.format(returnDate);
            Issue issue = new Issue(issueDate, returnDateString, student, book.get());
            issueRepository.save(issue);

            System.out.println("Book issued to the student successfully");
        }
    }

    public void listBooksByUsn() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the USN of the student: ");
        String usn = scanner.nextLine();
        List<Issue> issueList = issueRepository.findByIssueStudentUsn(usn);
        if(issueList.isEmpty()) {
            System.out.println("No books rented by the student with USN: " + usn);
        } else {
            System.out.println("Books rented by the student with USN: " + usn);
            System.out.println("Issue ID Book ISBN Book Title Issue Date Return Date");
            for(Issue issue : issueList) {
                System.out.println(issue.getIssueId() + " " + issue.getIssueBook().getIsbn() + " " + issue.getIssueBook().getTitle() + " " + issue.getIssueDate() + " " + issue.getReturnDate());
            }
        }
    }
}
