package app.controller;

import app.model.User;
import app.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/")
public class MainRestController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @GetMapping("userslist")
    public List<User> usersList() {
        return userService.findAll();
    }
    
    @GetMapping("update")
    public User getUser(@RequestParam(value = "id", required = true) String id) {
        long userId = Long.parseLong(id);

        return userService.findById(userId).get();
    }
    
    @PutMapping("update")
    public User updateUser(@RequestParam(value = "id", required = true) String id, @RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userService.save(user);
    }
    
    @DeleteMapping("delete")
    public void deleteUser(@RequestParam(value = "id", required = true) String id) {
        long userId = Long.parseLong(id);
        userService.deleteById(userId);
    }
}
