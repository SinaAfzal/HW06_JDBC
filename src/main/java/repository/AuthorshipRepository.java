package repository;

import connection.JDBCConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class AuthorshipRepository {
    private final JDBCConnection jdbcConnection = new JDBCConnection();
    final Connection connection = jdbcConnection.getConnection();
    private String serverMessage;

    AuthorshipRepository() throws SQLException {
    }

    public String getServerMessage() {
        return serverMessage;
    }

    public void setServerMessage(String serverMessage) {
        this.serverMessage = serverMessage;
    }
}
//**********************************************Developed by:***********************************************************
//******************************************* Sina Afzalsoltani ********************************************************
//===============================================Maktab 101=============================================================