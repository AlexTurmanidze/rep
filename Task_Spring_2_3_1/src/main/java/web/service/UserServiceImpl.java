package web.service;

import java.util.List;
import java.util.Optional;
import javax.persistence.PersistenceException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.model.User;

@Service("UserService")
@Repository
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    
    @Override
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }
    
    @Override
    public User save(User user) {
        User newUser = null;
        try {
            newUser = userRepository.save(user);
        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
        }
        return newUser;
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
