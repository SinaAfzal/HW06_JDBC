package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {
    private final Connection connection= DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","12369874");

    public JDBCConnection() throws SQLException {
    }

    public Connection getConnection() {
        return connection;
    }
}
