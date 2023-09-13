package repository;

import entity.Books;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BooksRepository extends AuthorshipRepository{
    public BooksRepository() throws SQLException {
    }

    public int countBooksOfAuthor(int authorID) throws SQLException {
        String countBooksQuery = "SELECT COUNT(*) FROM authorship WHERE author_id=?";
        PreparedStatement preparedStatementCountBooks = connection.prepareStatement(countBooksQuery);
        preparedStatementCountBooks.setInt(1, authorID);
        ResultSet resultSetBooksCount = preparedStatementCountBooks.executeQuery();
        resultSetBooksCount.next();
        return resultSetBooksCount.getInt("count");
    }

    public int countAuthorsOfBook(int bookID) throws SQLException {
        String counterQuery = "SELECT COUNT(*) FROM authorship a WHERE book_id=?";
        PreparedStatement counterPreparedStatement = connection.prepareStatement(counterQuery);
        counterPreparedStatement.setInt(1, bookID);
        ResultSet resultSet = counterPreparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getInt("count");
    }

    public String[] listOfAuthorNamesOfBook(int bookID) throws SQLException {
        String[] authorFullNames = new String[countAuthorsOfBook(bookID)];
        String listAuthorsOfBookQuery = "SELECT a.firstName, a.lastName FROM authors a INNER JOIN authorship a2 ON a.id = a2.author_id WHERE book_id=? ORDER BY a2.author_id";
        PreparedStatement listAuthorsPreparedStatement = connection.prepareStatement(listAuthorsOfBookQuery);
        listAuthorsPreparedStatement.setInt(1, bookID);
        ResultSet resultSet = listAuthorsPreparedStatement.executeQuery();
        if (countAuthorsOfBook(bookID) != 0) {
            for (int i = 0; i < authorFullNames.length; i++) {
                resultSet.next();
                authorFullNames[i] = resultSet.getString("firstName") + " " + resultSet.getString("lastName");
            }
            return authorFullNames;
        } else {
            setServerMessage("Database error: No authors found for this book!");
            return null;
        }
    }

    public int[] listOfAuthorIDsOfBook(int bookID) throws SQLException {
        int[] authorsIDs = new int[countAuthorsOfBook(bookID)];
        String listOfAuthorIDsofBookQuery = "SELECT a.author_id FROM authorship a WHERE book_id=? ORDER BY a.author_id";
        PreparedStatement listAuthorsIDsPreparedStatement = connection.prepareStatement(listOfAuthorIDsofBookQuery);
        listAuthorsIDsPreparedStatement.setInt(1, bookID);
        ResultSet resultSet = listAuthorsIDsPreparedStatement.executeQuery();
        if (countAuthorsOfBook(bookID) != 0) {
            for (int i = 0; i < authorsIDs.length; i++) {
                resultSet.next();
                authorsIDs[i] = resultSet.getInt("author_id");
            }
            return authorsIDs;
        } else {
            setServerMessage(getServerMessage() + "Database error: No authors found for this book!");
            return null;
        }
    }

    public Books[] loadBooksOfAuthor(int authorID) throws SQLException {
        Books[] books = new Books[countBooksOfAuthor(authorID)];
        String loadBooksQuery = "SELECT * FROM books b INNER JOIN authorship a ON b.id = a.book_id WHERE author_id=? ORDER BY b.pubyear";
        PreparedStatement preparedStatementBooksTable = connection.prepareStatement(loadBooksQuery);
        preparedStatementBooksTable.setInt(1, authorID);
        ResultSet resultSetBooks = preparedStatementBooksTable.executeQuery();
        if (books.length != 0) {
            for (int i = 0; i < books.length; i++) {
                resultSetBooks.next();
                books[i] = new Books(
                        resultSetBooks.getInt("id"),
                        resultSetBooks.getString("title"),
                        resultSetBooks.getInt("pubYear"),
                        listOfAuthorNamesOfBook(resultSetBooks.getInt("id")),
                        listOfAuthorIDsOfBook(resultSetBooks.getInt("id")));
            }
            return books;
        } else {
            setServerMessage("----We have no publications of this author on our database yet :(");
            return null;
        }
    }
}
