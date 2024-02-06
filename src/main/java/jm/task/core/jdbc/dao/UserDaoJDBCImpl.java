package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Connection connection;

    {
        try {
            connection = Util.getConnection();
            connection.setAutoCommit(false);

        } catch (SQLException e) {
            System.out.println("ERROR CONNECTION");
        }
    }
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try(Statement statement = connection.createStatement()){
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS user (" +
                    "  id BIGINT NOT NULL AUTO_INCREMENT," +
                    "  name VARCHAR(45) NOT NULL," +
                    "  lastName VARCHAR(45) NOT NULL," +
                    "  age TINYINT NOT NULL," +
                    "  PRIMARY KEY (id)," +
                    "  UNIQUE INDEX id_UNIQUE (id ASC) VISIBLE)");
            connection.commit();
            System.out.println("TABLE `user` created");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try(Statement statement = connection.createStatement()){
            statement.executeUpdate("DROP TABLE IF EXISTS user");
            connection.commit();
            System.out.println("TABLE `user` drop");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try(Statement statement = connection.createStatement()){
            String query = "INSERT INTO user (name, lastname, age) VALUES ('" + name + "', '" + lastName + "', " + age + ")";
            statement.executeUpdate(query);
            connection.commit();
            System.out.println("User save in TABLE `user`");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try(Statement statement = connection.createStatement()){
            String query = "DELETE FROM user WHERE id = \""+id+"\"";
            int i = statement.executeUpdate(query);
            connection.commit();
            System.out.println("Remove user from TABLE `user` where id " + i);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> result = new ArrayList<>();
        try(Statement statement = connection.createStatement()){
            String query = "SELECT * FROM user";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                User user = new User(resultSet.getString(2), resultSet.getString(3), resultSet.getByte(4));
                user.setId(resultSet.getLong(1));
                result.add(user);
                connection.commit();
            }
            connection.commit();
            System.out.println("All users from TABLE " + result);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public void cleanUsersTable() {
        try(Statement statement = connection.createStatement()){
            String query = "DELETE FROM user";
            int i = statement.executeUpdate(query);
            connection.commit();
            System.out.println("Delete users from TABLE `user`");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
