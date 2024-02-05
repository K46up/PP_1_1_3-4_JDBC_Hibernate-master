package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь

        Util.getConnection();
        UserDao userDao = new UserDaoJDBCImpl();

        userDao.createUsersTable();
        userDao.dropUsersTable();
        userDao.createUsersTable();
        userDao.dropUsersTable();

        Util.closeConnection();

/*        userDao.saveUser("Name1", "LastName1", (byte) 20);
        userDao.saveUser("Name2", "LastName2", (byte) 25);
        userDao.saveUser("Name3", "LastName3", (byte) 31);
        userDao.saveUser("Name4", "LastName4", (byte) 38);

        userDao.removeUserById(1);
        userDao.getAllUsers();
        userDao.cleanUsersTable();
        userDao.dropUsersTable();*/

/*        try {
            Util.getConnection();
            System.out.println("Connect");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Util.closeConnection();
        System.out.println("Disconnect");*/
    }
}
