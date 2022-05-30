package jm.task.core.hibernate.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public enum Util {
    INSTANCE;
    public SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/world?zeroDateTimeBehavior=CONVERT_TO_NULL");
        configuration.setProperty("hibernate.connection.username", "root");
        configuration.setProperty("hibernate.connection.password", "user");
        
        configuration.setProperty("hibernate.connection.pool_size", "1");
        
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.current_session_context_class", "thread");
        
        configuration.addResource("User.hbm.xml");
        
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        
        return sessionFactory;
    }
}
