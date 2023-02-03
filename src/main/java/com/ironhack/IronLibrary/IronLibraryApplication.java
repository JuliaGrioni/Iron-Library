package com.ironhack.IronLibrary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

import static com.ironhack.IronLibrary.classes.LibraryMethods.showMenu;

@SpringBootApplication
public class IronLibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(IronLibraryApplication.class, args);
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to the Iron's Library!");
		showMenu();
		System.out.println("Enter your choice: ");
		boolean execute=true;
		while (execute) {
			System.out.println("Choose an option number: ");
			String command= scanner.nextLine();
			switch (command){
				case "1":

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
