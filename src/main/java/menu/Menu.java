package menu;

import entity.Authors;
import entity.Books;
import service.AuthorsService;
import service.BooksService;

import java.sql.SQLException;
import java.util.Scanner;

public class Menu {
    Scanner scanner = new Scanner(System.in);
    AuthorsService authorsService = new AuthorsService();
    BooksService booksService = new BooksService();

    public Menu() throws SQLException {
    }

    public void userInterface() throws SQLException {

        boolean isTrue = true;
        while (isTrue) {
            System.out.println("+--------------------------------------------------+");
            System.out.println("|                   BOOK REPOSITORY                |");
            System.out.println("|           Developer by: Sina Afzalsoltani        |");
            System.out.println("+--------------------------------------------------+");
            System.out.println();
            System.out.println();
            System.out.println("Choose an option from the menu:");
            System.out.println("1- Add a new author to database");
            System.out.println("2- Load an author using the author's ID");
            System.out.println("3- Add a new book to database");
            System.out.println("4- Load a book using the book's ID");
            System.out.println("5- Delete a book from database");
            System.out.println("---Enter q to quit---");

            String input=scanner.nextLine();

            switch (input) {
                case "1" -> {
                    addAuthor();
                    scanner.nextLine();// this is to catch the break line from the previous nextLine(). without this after the end of the called method, a null input is automatically inserted which will cause the unwanted printing of the menu.
                    waitForUser();
                }
                case "2" -> {
                    loadAuthor();
                    scanner.nextLine();
                    waitForUser();
                }
                case "3" -> {
                    addBook();
                    scanner.nextLine();
                    waitForUser();
                }
                case "4" -> {
                    loadBook();
                    scanner.nextLine();
                    waitForUser();
                }
                case "5" -> {
                    deleteBook();
                    scanner.nextLine();
                    waitForUser();
                }
                case "q" -> {
                    isTrue = false;
                    scanner.nextLine();
                    waitForUser();
                }
                default -> {
                    System.out.println("Invalid input!");
                    scanner.nextLine();
                    waitForUser();
                }
            }
        }
    }

    public void addAuthor() throws SQLException {
        System.out.print("Author's first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Author's last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Author's age: ");
        int age = scanner.nextInt();

        authorsService.register(firstName, lastName, age);
    }

    public Authors loadAuthor() throws SQLException {
        System.out.println("Author's ID: ");
        int authorID = scanner.nextInt();
        return authorsService.load(authorID);
    }

    public void addBook() throws SQLException {
        System.out.print("Book's title: ");
        String title = scanner.nextLine();
        System.out.print("Publication year: ");
        int pubYear = scanner.nextInt();
        System.out.print("Author's/Authors' ID (in form of array eg. {1,2}): ");
        scanner.nextLine();
        int[] AuthorsIDs = stringToIntArray(scanner.nextLine());
        booksService.addBook(title, pubYear, AuthorsIDs);
    }

    public Books loadBook() throws SQLException {
        System.out.print("Book's ID: ");
        int bookID = scanner.nextInt();
        return booksService.loadBook(bookID);
    }

    public void deleteBook() throws SQLException {
        System.out.println("Book's ID: ");
        int bookID = scanner.nextInt();
        booksService.delete(bookID);
    }

    public int[] stringToIntArray(String stringOfArray) {
        stringOfArray = stringOfArray.replaceAll("[{}]", ""); // remove curly braces
        String[] stringArray = stringOfArray.split(","); // split by comma
        int[] intArray = new int[stringArray.length];
        for (int i = 0; i < stringArray.length; i++) {
            intArray[i] = Integer.parseInt(stringArray[i].trim()); // convert each string to int
        }
        return intArray;
    }

    public void waitForUser(){


        System.out.println();
        System.out.println("**** Press any key to continue ****");
        scanner.nextLine();

    }

}
//**********************************************Developed by:***********************************************************
//******************************************* Sina Afzalsoltani ********************************************************
//===============================================Maktab 101=============================================================