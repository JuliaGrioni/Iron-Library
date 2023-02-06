package com.ironhack.IronLibrary;

import com.ironhack.IronLibrary.classes.LibraryMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class IronLibraryApplication implements CommandLineRunner {
	@Autowired
	LibraryMethods libraryMethods;
	public static void main(String[] args) {
		SpringApplication.run(IronLibraryApplication.class, args);

	}
	@Override
	public void run(String... args) throws Exception{
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to the Iron's Library!");
		System.out.println("Enter your choice: ");
		boolean execute=true;
		while (execute) {
			System.out.println("Choose an option number: ");
			String command= scanner.nextLine();
			switch (command){
				case "1":
					libraryMethods.addBook();
					break;
				case "2":
					libraryMethods.searchBookByTitle();
					break;
				case "3":
					libraryMethods.searchBookByCategory();
					break;
				case "4":
					libraryMethods.searchBookByAuthor();
					break;
				case "5":
					libraryMethods.listAllBooks();
					break;
				case "6":
					libraryMethods.issueBookToStudent();
					break;
				case "7":
					libraryMethods.listBooksByUsn();
					break;
				case "8":
					System.out.println("Goodbye!");
					System.exit(0);
					break;
				default:
					System.out.println("Invalid option, please try again.");
					break;
			}
		}
		scanner.close();
	}

}
