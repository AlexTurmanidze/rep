package jm.task.core.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import jm.task.core.jdbc.model.User;
import java.util.List;
import java.util.Random;
import jm.task.core.jdbc.util.Util;

public class UserDaoJDBCImpl implements UserDao {
    private Connection con;
    
    public UserDaoJDBCImpl() {
        this.con = Util.INSTANCE.getConnection();
    }
    
    private static boolean isTableExists(Connection con) throws SQLException {
        ResultSet rs = con.getMetaData().getTables(null, "world", "USERS", new String[] {"TABLE"});
        return rs.next();
    }
    
    @Override
    public void createUsersTable() {
        try {
            if (!isTableExists(con)) {
                con.createStatement().executeUpdate("CREATE TABLE Users (ID BIGINT(255), NAME VARCHAR(20), LASTNAME VARCHAR(20), AGE TINYINT(3))");
                System.out.println("CREATE TABLE Users OK");
            } else {
                System.out.println("Таблица USERS уже существует!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void dropUsersTable() {
        try {
            if (isTableExists(con)) {
                con.createStatement().executeUpdate("DROP TABLE users");
                System.out.println("DROP TABLE Users OK");
            } else {
                System.out.println("Таблица USERS не существует!");
            }
        } catch (SQLException e) {
            System.out.println("DROP TABLE Users ERROR");
            e.printStackTrace();
        }
    }
    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            long id = new Random().nextInt();
            PreparedStatement saveUser = con.prepareStatement("INSERT INTO USERS VALUES (?, ?, ?, ?)");
            saveUser.setLong(1, id);
            saveUser.setString(2, name);
            saveUser.setString(3, lastName);
            saveUser.setByte(4, age);
            saveUser.executeUpdate();
            System.out.println("User " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void removeUserById(long id) {
        try {
            PreparedStatement removeUserById = con.prepareStatement("DELETE FROM USERS WHERE id = ?");
            removeUserById.setLong(1, id);
            removeUserById.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<User> getAllUsers() {
        List<User> list = null;
        try {
            list = new ArrayList<>();
            ResultSet rs = con.prepareStatement("SELECT * FROM USERS").executeQuery();
            while (rs.next()) {
                User user = new User(rs.getString(2), rs.getString(3), rs.getByte(4));
                user.setId(rs.getLong(1));
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    @Override
    public void cleanUsersTable() {
        try {
            con.createStatement().executeUpdate("TRUNCATE TABLE USERS");
            System.out.println("Таблица USERS очищена");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void close() {
        try {
            System.out.println("Close Connection");
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
