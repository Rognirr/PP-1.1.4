package jm.task.core.jdbc.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import jm.task.core.jdbc.model.User;

public final class Util {
    // Соеденение с БД
    private static final String DB_URL = "hibernate.connection.url";
    private static final String DB_USER_NAME = "hibernate.connection.username";
    private static final String DB_PASSWORD = "hibernate.connection.password";
    private static final SessionFactory SESSION_FACTORY;

    static {
        Configuration configuration = new Configuration().addAnnotatedClass(User.class);

        SESSION_FACTORY = configuration.buildSessionFactory(
                new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build());
    }

    private Util() {
    }

    public static Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER_NAME, DB_PASSWORD);
            connection.setAutoCommit(false);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }
}
