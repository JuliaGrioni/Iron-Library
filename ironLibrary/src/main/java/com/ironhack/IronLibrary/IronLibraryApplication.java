package com.ironhack.IronLibrary;

import com.ironhack.IronLibrary.methods.LibraryMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

////////////////////////////////////////////////////////////////////////
// Uncomment the following code to use the application

//@SpringBootApplication
//public class IronLibraryApplication implements CommandLineRunner {
//	@Autowired
//	LibraryMethods libraryMethods;
//	public static void main(String[] args) {
//		SpringApplication.run(IronLibraryApplication.class, args);
//	}
//	@Override
//	public void run(String... args) throws Exception{
//		Scanner scanner = new Scanner(System.in);
//		System.out.println("Welcome to the Iron's Library!");
//		boolean execute = true;
//		while (execute) {
//			System.out.println("\nMenu:");
//			System.out.println("1. Add a book");
//			System.out.println("2. Search book by title");
//			System.out.println("3. Search book by category");
//			System.out.println("4. Search book by Author");
//			System.out.println("5. List all books along with author");
//			System.out.println("6. Issue book to student");
//			System.out.println("7. List books by usn");
//			System.out.println("8. Exit");
//			System.out.print("\nEnter your choice: ");
//			String command = scanner.nextLine();
//			switch (command){
//				case "1":
//					System.out.println("Enter isbn: ");
//					String isbn = scanner.nextLine();
//					System.out.println("Enter title: ");
//					String title = scanner.nextLine();
//					System.out.println("Enter category: ");
//					String category= scanner.nextLine();
//					System.out.println("Enter author name: ");
//					String name = scanner.nextLine();
//					System.out.println("Enter author mail: ");
//					String email = scanner.nextLine();
//					System.out.println("Enter number of books: ");
//					int quantity = scanner.nextInt();
//					scanner.nextLine();
//					libraryMethods.addBook(isbn, title, category, name, email, quantity);
//					break;
//				case "2":
//					System.out.println("Write the book's title: ");
//					title = scanner.nextLine();
//					libraryMethods.searchBookByTitle(title);
//					break;
//				case "3":
//					System.out.println("Write the book's category: ");
//					category = scanner.nextLine();
//					libraryMethods.searchBookByCategory(category);
//					break;
//				case "4":
//					System.out.println("Enter author name: ");
//					String authorName = scanner.nextLine();
//					libraryMethods.searchBookByAuthor(authorName);
//					break;
//				case "5":
//					libraryMethods.listAllBooks();
//					break;
//				case "6":
//					System.out.println("Enter student USN: ");
//					String usn = scanner.nextLine();
//					System.out.println("Enter student name: ");
//					name = scanner.nextLine();
//					System.out.println("Enter book title: ");
//					title = scanner.nextLine();
//					libraryMethods.issueBookToStudent(usn, name, title);
//					break;
//				case "7":
//					System.out.print("Enter the USN of the student: ");
//					usn = scanner.nextLine();
//					libraryMethods.listBooksByUsn(usn);
//					break;
//				case "8":
//					System.out.println("Goodbye!");
//					System.exit(0);
//					break;
//				default:
//					System.out.println("Invalid option, please try again.");
//					break;
//			}
//		}
//		scanner.close();
//	}
//
//}




////////////////////////////////////////////////////////////////////////
// Uncomment the following code to testing
@SpringBootApplication
public class IronLibraryApplication {
	@Autowired
	LibraryMethods libraryMethods;
	public static void main(String[] args) {
		SpringApplication.run(IronLibraryApplication.class, args);
	}
}