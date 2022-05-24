package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    public static Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/world?zeroDateTimeBehavior=CONVERT_TO_NULL", "root", "user");
            System.out.println("Connection OK");
        } catch (SQLException e) {
            System.out.println("Connection ERROR");
            e.printStackTrace();
        }
        
        return con;
    }
}
