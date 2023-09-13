package repository;

import entity.Authors;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AuthorsRepository extends AuthorshipRepository{
    public AuthorsRepository() throws SQLException {
    }


    public void save(Authors authors) throws SQLException {
        String saveAuthorQuery = "INSERT INTO authors (firstname, lastname, age) VALUES (?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(saveAuthorQuery, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, authors.getFirstName());
        preparedStatement.setString(2, authors.getLastName());
        preparedStatement.setInt(3, authors.getAge());
        preparedStatement.executeUpdate();
        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        while (resultSet.next()) {
            authors.setId(resultSet.getInt(1));

        }
    }

    public Authors load(int authorID) throws SQLException {
        String loadAuthorQuery = "SELECT * FROM authors WHERE id=?";
        PreparedStatement preparedStatementAuthorsTable = connection.prepareStatement(loadAuthorQuery);
        preparedStatementAuthorsTable.setInt(1, authorID);
        ResultSet resultSetAuthorsTable = preparedStatementAuthorsTable.executeQuery();

        if (resultSetAuthorsTable.next()) {
            BooksRepository booksRepository = new BooksRepository();
            Authors author = new Authors(
                    resultSetAuthorsTable.getInt("id"),
                    resultSetAuthorsTable.getString("firstName"),
                    resultSetAuthorsTable.getString("lastName"),
                    resultSetAuthorsTable.getInt("age"),
                    booksRepository.loadBooksOfAuthor(authorID));
            return author;
        } else {
            setServerMessage("Database error: Author ID is not valid!");
            return null;
        }
    }
}
//**********************************************Developed by:***********************************************************
//******************************************* Sina Afzalsoltani ********************************************************
//===============================================Maktab 101=============================================================