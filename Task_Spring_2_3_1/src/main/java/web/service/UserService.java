package web.service;

import java.util.List;
import java.util.Optional;
import web.model.User;

public interface UserService {
    List<User> findAll();
    
    User save(User user);
    
    Optional<User> findById(Long id);
    
    void deleteById(Long id);
}
