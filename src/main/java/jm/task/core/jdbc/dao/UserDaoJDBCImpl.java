package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
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
            statement.execute("CREATE TABLE IF NOT EXISTS user (" +
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
            statement.execute("DROP TABLE IF EXISTS user");
            connection.commit();
            System.out.println("TABLE `user` drop");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {

    }

    public void removeUserById(long id) {

    }

    public List<User> getAllUsers() {
        return null;
    }

    public void cleanUsersTable() {

    }
}
