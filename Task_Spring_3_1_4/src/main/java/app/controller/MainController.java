package app.controller;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.ModelMap;
import app.model.Role;
import app.model.User;
import app.service.UserService;

@Controller
public class MainController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    
    @GetMapping("/admin/")
    public String list(Authentication authentication, ModelMap model) {
        User user = userService.findByEmail(authentication.getName()).get();
        Set<String> rolesNames = AuthorityUtils.authorityListToSet(user.getAuthorities());

        model.addAttribute("user", user);
        model.addAttribute("rolesNameList", rolesNames);

        return "userslist";
    }
    
    @GetMapping("/register")
    public String regForm(ModelMap model) {
        model.addAttribute("user", new User());
        return "register";
    }
    
    @PostMapping("/register")
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
    
    @GetMapping("/user")
    public String userPage(Authentication authentication, ModelMap model) {
        User user = userService.findByEmail(authentication.getName()).get();
        model.addAttribute("user", user);
        return "user";
    }
}
