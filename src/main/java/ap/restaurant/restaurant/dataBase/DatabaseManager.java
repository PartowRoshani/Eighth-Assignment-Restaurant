package ap.restaurant.restaurant.dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/Restaurant";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "Partow@1384";

    public  static Connection connect() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }
}
