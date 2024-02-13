package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private final SessionFactory sessionFactory = Util.getFactory();

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (Session session = sessionFactory.openSession()) {

            session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS user (" +
                    "  id BIGINT NOT NULL AUTO_INCREMENT," +
                    "  name VARCHAR(45) NOT NULL," +
                    "  lastName VARCHAR(45) NOT NULL," +
                    "  age TINYINT NOT NULL," +
                    "  PRIMARY KEY (id)," +
                    "  UNIQUE INDEX id_UNIQUE (id ASC) VISIBLE)").executeUpdate();
            session.getTransaction().commit();

            System.out.println("TABLE `user` created");
        } catch (HibernateException e) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = sessionFactory.openSession()) {

            session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS User").executeUpdate();
            session.getTransaction().commit();

            System.out.println("TABLE `user` drop");
        } catch (HibernateException e) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        try (Session session = sessionFactory.openSession()) {

            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();

            System.out.println("User " + name + " save in TABLE `user`");
        } catch (HibernateException e) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = sessionFactory.openSession()) {

            session.beginTransaction();
            User user = session.get(User.class, id);
            if (user != null) {
                session.delete(user);
            }
            session.getTransaction().commit();

            System.out.println("Remove user from TABLE `user` where id " + id);
        } catch (HibernateException e) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> result;
        try (Session session = sessionFactory.openSession()) {

            session.beginTransaction();
            result = session.createQuery("from User", User.class).getResultList();
            session.getTransaction().commit();

            System.out.println("All users from TABLE " + result);
        } catch (HibernateException e) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public void cleanUsersTable() {

        try (Session session = sessionFactory.openSession()) {

            session.beginTransaction();
            session.createQuery("delete from User").executeUpdate();
            session.getTransaction().commit();

            System.out.println("Delete users from TABLE `user`");
        } catch (HibernateException e) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            throw new RuntimeException(e);
        }
    }
}
