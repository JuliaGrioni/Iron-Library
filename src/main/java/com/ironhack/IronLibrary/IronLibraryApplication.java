package com.ironhack.IronLibrary;

import com.ironhack.IronLibrary.classes.LibraryMethods;
import com.ironhack.IronLibrary.model.Book;
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
		libraryMethods.showMenu();
		System.out.println("Enter your choice: ");
		boolean execute=true;
		while (execute) {
			System.out.println("Choose an option number: ");
			String command= scanner.nextLine();
			switch (command){
				case "1":
					libraryMethods.addBook(new Book());
					break;
				case "EXIT":
					execute= false;
					break;
				default: System.out.println("type HELP to show commands!");
					System.out.println("commands not exist!");
			}
		}
		scanner.close();
	}

}
