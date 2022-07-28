package app.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import app.model.User;
import app.dao.UserRepository;

@Repository
@Service("UserService")
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private UserRepository userRepository;
        
    @Override
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }
    
    @Override
    public User save(User user) {
        Optional<User> optional = userRepository.findByEmail(user.getEmail());
        if (optional.isPresent() && user.getId() != null) {
            if (user.getId().compareTo(optional.get().getId()) == 0) {
                return userRepository.save(user);
            }
        } else if (!optional.isPresent()) {
            return userRepository.save(user);
        }
        return user;
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
    
    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByEmail(username).get();
        if (user == null) {
            throw new UsernameNotFoundException("User with username [" + username + "] not found in the system");
        }
        return user;
    }
    
    @Override
    public long count() {
        return userRepository.count();
    }
}
