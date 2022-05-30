package jm.task.core.hibernate.service;

import java.util.List;
import jm.task.core.hibernate.model.User;
import jm.task.core.hibernate.dao.UserDao;
import jm.task.core.hibernate.dao.UserDaoHibernateImpl;

public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public UserServiceImpl() {
        this.userDao = new UserDaoHibernateImpl();
    }
    
    @Override
    public void createUsersTable() {
        userDao.createUsersTable();
    }
    @Override
    public void dropUsersTable() {
        userDao.dropUsersTable();
    }
    @Override
    public void saveUser(String name, String lastName, byte age) {
        userDao.saveUser(name, lastName, age);
    }
    @Override
    public void removeUserById(long id) {
        userDao.removeUserById(id);
    }
    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }
    @Override
    public void cleanUsersTable() {
        userDao.cleanUsersTable();
    }
    @Override
    public void close() {
        userDao.close();
    }   
}
