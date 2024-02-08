package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь

        UserServiceImpl userServiceImpl = new UserServiceImpl();

        userServiceImpl.dropUsersTable();
        userServiceImpl.createUsersTable();
        userServiceImpl.dropUsersTable();
        userServiceImpl.createUsersTable();

        userServiceImpl.saveUser("Name1", "LastName1", (byte) 20);
        userServiceImpl.saveUser("Name2", "LastName2", (byte) 25);
        userServiceImpl.saveUser("Name3", "LastName3", (byte) 31);
        userServiceImpl.saveUser("Name4", "LastName4", (byte) 38);
        userServiceImpl.saveUser("Name5", "LastName5", (byte) 48);
        userServiceImpl.saveUser("Name6", "LastName6", (byte) 8);

        userServiceImpl.removeUserById(1);
        userServiceImpl.removeUserById(3);
        userServiceImpl.removeUserById(5);

        userServiceImpl.getAllUsers();


        userServiceImpl.cleanUsersTable();
        userServiceImpl.getAllUsers();
        userServiceImpl.dropUsersTable();
        Util.closeConnection();

    }
}
