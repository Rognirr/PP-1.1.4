package jm.task.core.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

public class UserDaoJDBCImpl implements UserDao {
    private final String sqlCreateTable =
            "CREATE TABLE IF NOT EXISTS users" + " (id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, NAME VARCHAR(50)," +
                    " lastName VARCHAR(50), age TINYINT(3))";
    private final String sqlDropTable = "DROP TABLE IF EXISTS users";
    private final String sqlInsertUser = "INSERT INTO users (name, lastName, age) VALUES(?, ?, ?);";
    private final String sqlRemoveUserById = "DELETE FROM users WHERE (id = ?);";
    private final String sqlGetAllUsers = "SELECT * FROM users;";
    private final String sqlCleanUsersTable = "TRUNCATE users;";

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection connection = Util.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlCreateTable)) {
                preparedStatement.execute();
                connection.commit();
            } catch (SQLException throwable) {
                connection.rollback();
                throwable.printStackTrace();
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Connection connection = Util.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlDropTable)) {
                preparedStatement.execute();
                connection.commit();
            } catch (SQLException throwable) {
                connection.rollback();
                throwable.printStackTrace();
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlInsertUser)) {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, lastName);
                preparedStatement.setByte(3, age);
                preparedStatement.executeUpdate();
                connection.commit();
                System.out.println("User с именем – " + name + " добавлен в базу данных");
            } catch (SQLException throwable) {
                connection.rollback();
                throwable.printStackTrace();
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = Util.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlRemoveUserById)) {
                preparedStatement.setLong(1, id);
                preparedStatement.executeUpdate();
                connection.commit();
            } catch (SQLException throwable) {
                connection.rollback();
                throwable.printStackTrace();
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try (Connection connection = Util.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlGetAllUsers)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getLong("id"));
                    user.setName(resultSet.getString("name"));
                    user.setLastName(resultSet.getString("lastName"));
                    user.setAge(resultSet.getByte("age"));
                    users.add(user);
                }
                connection.commit();
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return users;
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlCleanUsersTable)) {
                preparedStatement.executeUpdate();
                connection.commit();
            } catch (SQLException throwable) {
                connection.rollback();
                throwable.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
