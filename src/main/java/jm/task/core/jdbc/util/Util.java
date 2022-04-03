package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // Соеденение с БД
    private static final String dbURL = "jdbc:mysql://localhost:3306/db";
    private static final String dbUserName = "Loker257";
    private static final String dbPassword = "Loker257";
    private static final SessionFactory sessionFactory;

    static {
        Configuration configuration = new Configuration().addAnnotatedClass(User.class);

        sessionFactory = configuration.buildSessionFactory(new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build());
    }

    public static Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection(dbURL, dbUserName, dbPassword);
            connection.setAutoCommit(false);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
