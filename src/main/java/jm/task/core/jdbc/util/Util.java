package jm.task.core.jdbc.util;

import javax.sql.ConnectionEvent;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String URL = "jdbc:mysql://localhost:3306/db_pp_1";
    private static final String USER = "root";
    private static final String PASSWORD = "java";
    private static Connection connection; //= DriverManager.getConnection(URL, USER, PASSWORD);

    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
