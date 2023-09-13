package service;

import entity.Books;
import repository.BooksRepository;

import java.sql.SQLException;

public class BooksService {
    BooksRepository booksRepository = new BooksRepository();

    public BooksService() throws SQLException {
    }

    public void addBook(String title, int pubYear, int[] authorsIDs) throws SQLException {
        Books book = new Books(null, title, pubYear, null, authorsIDs);
        booksRepository.save(book);
        if (book.getId() != null) {
            System.out.println("Book with ID " + book.getId() + " is added to the database successfully!");
        } else {
            System.out.println("Something went wrong :( -- Adding book was failed!");
        }
    }

    public Books loadBook(int bookID) throws SQLException {
        Books book = booksRepository.load(bookID);
        System.out.println(booksRepository.getServerMessage());
        System.out.println();
        if (book != null) {
            System.out.println("- Title: " + book.getTitle());
            System.out.println("- Publication year: " + book.getPubYear());
            switch (book.getAuthorsFullNames().length) {
                case 0 -> System.out.println("No authors found!");
                case 1 ->
                        System.out.println("- Author: " + book.getAuthorsFullNames()[0] + "--ID: " + book.getAuthorsIDs()[0]);
                default -> {
                    System.out.println("- Authors: ");
                    for (int i = 0; i < book.getAuthorsFullNames().length; i++) {
                        System.out.println("            " + (i + 1) + ") " + book.getAuthorsFullNames()[i] + "--ID: " + book.getAuthorsIDs()[i]);
                    }
                }
            }
        }
        return book;
    }

    public void delete(int bookID) throws SQLException {
        Books book = booksRepository.load(bookID);
        if (book == null) {
            System.out.println(booksRepository.getServerMessage());
        } else {
            booksRepository.delete(book);
            System.out.println(booksRepository.getServerMessage());
        }
    }
}
//**********************************************Developed by:***********************************************************
//******************************************* Sina Afzalsoltani ********************************************************
//===============================================Maktab 101=============================================================