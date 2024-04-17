package de.ait.users.repository.impl;

import de.ait.users.model.User;
import de.ait.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.sql.DriverManager.getConnection;

public class UserRepositoryJDBCImpl implements UserRepository {
    // 1. Connection
    // 2. SQL Statement
    // 3. Result Set

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String PASSWORD = "password";
    public static final String EMAIL = "email";
    public static final String AGE = "age";

    @Value("${db.driver}")
    private String dbDriver;
    @Value("${db.url}")
    private String dbAddress;
    @Value("${db.database}")
    private String dbName;
    @Value("${db.username}")
    private String dbUserName;
    @Value("${db.password}")
    private String dbPassword;

    private Connection getConnection() {

        String connectionStr = String.format("%s%s?user=%s&password=%s",
                dbAddress, dbName, dbUserName, dbPassword);
        try {

            Class.forName(dbDriver);
            return DriverManager.getConnection(connectionStr);

        } catch (SQLException e) {
            System.out.println("Connection error");
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            System.out.println("Driver DB not found");
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> findAll() {
        try (
                Connection connection = getConnection();
        ) {

            String sql = "SELECT * FROM t_user";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                //long id = resultSet.getLong(1);
                long id = resultSet.getLong(ID);
                String name = resultSet.getString(NAME);
                String email = resultSet.getString(EMAIL);
                String password = resultSet.getString(PASSWORD);
                int age = resultSet.getInt(AGE);

                User user = new User(id, name, password, email, age);
                users.add(user);

            }
            return users;

        } catch (Exception ex) {
            throw new RuntimeException();
        }

    }

    @Override
    public Optional<User> findById(Long id) {
        try (Connection connection = getConnection();) {
            String sql = String.format("SELECT * FROM t_user WHERE id=%d", id);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet == null || !resultSet.next()) {
                return Optional.empty();
            } else {
                long useriId = resultSet.getLong(ID);
                String name = resultSet.getString(NAME);
                String email = resultSet.getString(EMAIL);
                String password = resultSet.getString(PASSWORD);
                int age = resultSet.getInt(AGE);

                User user = new User(id, name, password, email, age);
                return Optional.of(user);
            }
        } catch (Exception ex) {
            throw new RuntimeException("User by id not find");
        }
    }

    @Override
    public User save(User user) {
        String sql = "INSERT INTO t_user (name, password, email, age) VALUES (?, ?, ?, ?)";

        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setInt(4, user.getAge());

            int rowAffected = statement.executeUpdate();

            if (rowAffected == 1) {
                ResultSet resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    long newId = resultSet.getLong(1);
                    user.setId(newId);
                    return user;
                }
            }

        } catch (Exception ex) {
            throw new RuntimeException();
        }
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
