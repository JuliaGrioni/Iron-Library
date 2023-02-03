package com.ironhack.IronLibrary.classes;

import com.ironhack.IronLibrary.model.Book;

import java.util.Scanner;

public class LibraryMethods {
    public static void showMenu(){
        System.out.println("1- Add a book \n" +
                        "2- Search book by title \n" +
                        "3- Search book by category\n" +
                        "4- Search book by Author \n" +
                        "5- List all books along with author\n" +
                        "6- Issue book to student\n" +
                        "7- List books by usn\n" +
                        "8- Exit");
    }

    public static void addBook(Book book){
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


    }
}
