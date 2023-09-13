package service;

import entity.Authors;
import repository.AuthorsRepository;

import java.sql.SQLException;

public class AuthorsService {
    AuthorsRepository authorsRepository = new AuthorsRepository();

    public AuthorsService() throws SQLException {
    }

    public void register(String firstName, String lastName, int age) throws SQLException {
        Authors author = new Authors(null, firstName, lastName, age, null);
        authorsRepository.save(author);
        if (author.getId() != null)
            System.out.println("Author with ID " + author.getId() + " is registered successfully!");
        else
            System.out.println("Something went wrong :( -- Registration failed!");
    }

    public Authors load(int authorID) throws SQLException {
        Authors author = authorsRepository.load(authorID);
        if (author != null) {
            System.out.println("Author with ID " + authorID + " is loaded successfully: ");
            System.out.println();
            System.out.println("- First name: " + author.getFirstName());
            System.out.println("- Last name: " + author.getLastName());
            System.out.println("- Age: " + author.getAge());
            if (author.getBooks() != null) {
                System.out.println("---- List of publications in chronological order: ");
                for (int i = 0; i < author.getBooks().length; i++) {
                    System.out.println("     -> " + (i + 1) + ") " + author.getBooks()[i].getTitle() + "-" + author.getBooks()[i].getPubYear());
                    if (author.getBooks()[i].getAuthorsFullNames().length > 1) {
                        System.out.println("           + This book has the following co-authors!: ");
                        int counterOfCoAuthors = 1;
                        for (int j = 0; j < author.getBooks()[i].getAuthorsFullNames().length; j++) {
                            if (author.getBooks()[i].getAuthorsIDs()[j] != authorID) {
                                System.out.println("            " + counterOfCoAuthors++ + ") " + author.getBooks()[i].getAuthorsFullNames()[j]);
                            }
                        }
                    }
                }
            } else {
                System.out.println("----We have no publications of this author on our database yet :(");
            }
            return author;
        } else {
            System.out.println(authorsRepository.getServerMessage());
            return null;
        }
    }
}
