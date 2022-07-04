package web.service;

import java.util.List;
import java.util.Optional;
import javax.persistence.PersistenceException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import web.dao.UserDao;
import web.model.User;

@Service("UserService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    
    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }
    
    @Override
    public User save(User user) {
        try {
            return userDao.save(user);
        } catch (PersistenceException | JpaSystemException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }

    @Override
    public Optional<User> findById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        userDao.deleteById(id);
    }
}
