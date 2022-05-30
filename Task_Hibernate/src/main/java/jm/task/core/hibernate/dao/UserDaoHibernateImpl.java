package jm.task.core.hibernate.dao;

import java.util.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.jdbc.ReturningWork;
import jm.task.core.hibernate.model.User;
import jm.task.core.hibernate.util.Util;

public class UserDaoHibernateImpl implements UserDao {
    private SessionFactory sessionFactory;
    
    public UserDaoHibernateImpl() {
        this.sessionFactory = Util.INSTANCE.getSessionFactory();
    }

    private static boolean isTableExists(Session session) {
        return session.doReturningWork(new ReturningWork<Boolean>() {
            @Override
            public Boolean execute(Connection con) throws SQLException {
                ResultSet rs = con.getMetaData().getTables(null, "world", "USER", new String[]{"TABLE"});
                return rs.next();
            }
        });
    }

    @Override
    public void createUsersTable() {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            if (!isTableExists(session)) {
                NativeQuery query = session.createSQLQuery("CREATE TABLE User (ID BIGINT(255), NAME VARCHAR(20), LASTNAME VARCHAR(20), AGE TINYINT(3))");
                tx = session.beginTransaction();
                query.executeUpdate();
                tx.commit();
            }
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        }
    }
    @Override
    public void dropUsersTable() {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            if (isTableExists(session)) {
                NativeQuery query = session.createSQLQuery("DROP TABLE User");
                tx = session.beginTransaction();
                query.executeUpdate();
                tx.commit();
            }
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        }
    }
    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.save(new User(name, lastName, age));
            tx.commit();
            System.out.println("User " + name + " добавлен в базу данных");
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        }
    }
    @Override
    public void removeUserById(long id) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("Delete User where id = :id")
                    .setParameter("id", id);
            tx = session.beginTransaction();
            query.executeUpdate();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        }
    }
    @Override
    public List<User> getAllUsers() {
        List<User> list = null;
        try (Session session = sessionFactory.openSession()) {
            list = session.createQuery("From User").list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    @Override
    public void cleanUsersTable() {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("Delete User");
            tx = session.beginTransaction();
            query.executeUpdate();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        }
    }
    @Override
    public void close() {
        sessionFactory.close();
    }
}
