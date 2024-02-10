package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.sql.ConnectionEvent;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String URL = "jdbc:mysql://localhost:3306/db_pp_1";
    private static final String USER = "root";
    private static final String PASSWORD = "java";
    private static Connection connection;
    private static SessionFactory sessionFactory;

    public static Connection getConnection() {
        try {
            if (connection == null) {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static SessionFactory getFactory(){
        try {
            // Создаем объект конфигурации Hibernate
            Configuration configuration = new Configuration();

            // Добавляем аннотированные классы в конфигурацию
            configuration.addAnnotatedClass(User.class);

            // Настраиваем свойства подключения к базе данных
            configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
            configuration.setProperty("hibernate.connection.url", URL);
            configuration.setProperty("hibernate.connection.username", USER);
            configuration.setProperty("hibernate.connection.password", PASSWORD);

            // Настраиваем режим создания таблиц (в данном случае - создание таблиц при необходимости)
            configuration.setProperty("hibernate.hbm2ddl.auto", "update");

            // Создаем фабрику сессий
            sessionFactory = configuration.buildSessionFactory();

        } catch (Throwable ex) {
            // Ловим и выводим исключения
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        return sessionFactory;
    }
    public static void closeSessionFactory() {
        sessionFactory.close();
    }

}
