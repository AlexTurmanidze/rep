package app.controller;

import app.model.Role;
import app.model.User;
import app.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @GetMapping
    public String getRegForm(ModelMap model) {
        model.addAttribute("user", new User());
        return "register";
    }
            
    @PostMapping
    public String registerUser(@ModelAttribute User user, ModelMap model) {
        model.addAttribute("user", user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if (userService.count() == 0) {
            user.getRoles().add(new Role("ADMIN"));
        } else {
            user.getRoles().add(new Role("USER"));
        }
        
        userService.save(user);
        
        return "redirect:/login";
    }
}
