package app.controller;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    
    @GetMapping("/admin")
    public String list(Authentication authentication, ModelMap model) {
        User user = userService.findByEmail(authentication.getName()).get();
        Set<String> roles = AuthorityUtils.authorityListToSet(user.getAuthorities());
        if (roles.contains("ADMIN")) {
            List<User> listUsers = userService.findAll();
            model.addAttribute("user", user);
            model.addAttribute("rolesList", roles);
            model.addAttribute("users", listUsers);
        } else if (roles.isEmpty()) {
            return "redirect:/";
        } else {
            return "redirect:/user";
        }
        return "userslist";
    }    
    @GetMapping("/admin/update")
    public String newForm(@RequestParam(value = "id", required = true) String id, ModelMap model) {
        long userId = Long.parseLong(id);
        User user = userService.findById(userId).get();
        List<Role> listRoles = new ArrayList<>();
        listRoles.add(new Role("ADMIN"));
        listRoles.add(new Role("USER"));
        
        model.addAttribute("user", user);
        model.addAttribute("listRoles", listRoles);
        
        return "usersEdit";
    }
    @PostMapping("/admin/update")
    public String updateForm(@ModelAttribute User user, ModelMap model) {
        model.addAttribute("user", user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);
        
        return "redirect:/admin/";
    }
    @GetMapping("/admin/delete")
    public String deleteForm(@RequestParam(value = "id", required = true) String id) {
        long userId = Long.parseLong(id);
        userService.deleteById(userId);

        return "redirect:/admin/";
    }
    
    @GetMapping("/register")
    public String RegForm(ModelMap model) {
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
