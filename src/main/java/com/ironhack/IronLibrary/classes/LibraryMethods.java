package com.ironhack.IronLibrary.classes;
import com.ironhack.IronLibrary.model.Author;
import com.ironhack.IronLibrary.model.Book;
import com.ironhack.IronLibrary.repository.AuthorRepository;
import com.ironhack.IronLibrary.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
@Component
public class LibraryMethods {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    public void showMenu(){
        System.out.println("1- Add a book \n" +
                        "2- Search book by title \n" +
                        "3- Search book by category\n" +
                        "4- Search book by Author \n" +
                        "5- List all books along with author\n" +
                        "6- Issue book to student\n" +
                        "7- List books by usn\n" +
                        "8- Exit");
    }

    public void addBook(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter isbn: ");
        String isbn= scanner.nextLine();
        System.out.println("Enter title: ");
        String title= scanner.nextLine();
        System.out.println("Enter category: ");
        String category= scanner.nextLine();
        System.out.println("Enter author name: ");
        String name= scanner.nextLine();
        System.out.println("Enter author mail: ");
        String email= scanner.nextLine();
        System.out.println("Enter number of books: ");
        int quantity= scanner.nextInt();

        Author author;
        Optional<Author> optionalAuthor = authorRepository.findByName(name);
        if(!optionalAuthor.isPresent()){
            author= new Author(name, email);
            authorRepository.save(author);
        }else{
            author = optionalAuthor.get();
        }


        Book book1= new Book(isbn, category,title, quantity, author);
        bookRepository.save(book1);

    }

    public void findByTitle(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write the book's title: ");
        String title= scanner.nextLine();
       Optional<Book> optionalBook= bookRepository.findByTitle(title);
       if(optionalBook.isPresent()){
           System.out.println(optionalBook.get());
       }
       else {
           System.out.println("This title doesn't exists");
       }
    }

    public void findByCategory (){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write the book's category: ");
        String category= scanner.nextLine();
        List<Book> bookList = bookRepository.findByCategory(category);
        if(bookList.size() <= 0){
            System.out.println("No hemos encontrado");
        }
        else {
            System.out.println(bookList);
        }
    }

    public void findByAuthor() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write the book's author: ");
        String name = scanner.nextLine();
        Optional<Author> optionalAuthor = authorRepository.findByName(name);
        if (optionalAuthor.isPresent()) {
            List<Book> bookAuthor = bookRepository.findByAuthor(optionalAuthor.get());
            if (bookAuthor.size() > 0) {
                System.out.println(bookAuthor);
            }
        } else {
            System.out.println("no existe el autor en la base de datos");
        }
    }
}
