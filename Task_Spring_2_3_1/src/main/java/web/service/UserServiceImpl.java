package web.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
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
        return userDao.save(user);
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
