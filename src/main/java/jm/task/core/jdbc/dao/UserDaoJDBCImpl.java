package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection = Util.getConnection();


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

            //connection.commit();

            System.out.println("TABLE `user` created");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {

        String query = "DROP TABLE IF EXISTS user";

        try(Statement statement = connection.createStatement()){

            statement.executeUpdate(query);
            //connection.commit();

            System.out.println("TABLE `user` drop");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        String query = "INSERT INTO user (name, lastname, age) VALUES (?,?,?)";

        try(PreparedStatement statement = connection.prepareStatement(query)){

            statement.setString(1,name);
            statement.setString(2,lastName);
            statement.setByte(3,age);

            statement.executeUpdate();
            //connection.commit();

            System.out.println("User " + name + " save in TABLE `user`");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        String query = "DELETE FROM user WHERE id = ?";
        try(PreparedStatement statement = connection.prepareStatement(query)){

            statement.setLong(1,id);

            statement.executeUpdate();
            //connection.commit();

            System.out.println("Remove user from TABLE `user` where id " + id);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {

        List<User> result = new ArrayList<>();
        String query = "SELECT * FROM user";

        try(Statement statement = connection.createStatement()){

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                User user = new User(resultSet.getString(2), resultSet.getString(3), resultSet.getByte(4));
                user.setId(resultSet.getLong(1));
                result.add(user);
                //connection.commit();
            }

            System.out.println("All users from TABLE " + result);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public void cleanUsersTable() {

        String query = "DELETE FROM user";

        try(Statement statement = connection.createStatement()){

            statement.executeUpdate(query);
            //connection.commit();

            System.out.println("Delete users from TABLE `user`");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
