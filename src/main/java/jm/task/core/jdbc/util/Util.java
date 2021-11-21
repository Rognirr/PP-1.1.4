package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // Соеденение с БД
    private static final String dbURL = "jdbc:mysql://localhost:3306/db";
    private static final String dbUserName = "Loker257";
    private static final String dbPassword = "Loker257";

    public static Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection(dbURL, dbUserName, dbPassword);
            Class.forName("com.mysql.cj.jdbc.Driver");
            return connection;
        } catch (
                SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
