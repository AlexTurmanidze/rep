package app.controller;

import app.model.Role;
import app.model.User;
import app.service.UserService;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin/")
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @GetMapping
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
    @GetMapping("user/update")
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
    @PostMapping("user/update")
    public String updateForm(@ModelAttribute User user, ModelMap model) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);
        
        return "redirect:/admin/";
    }
    @GetMapping("/user/delete")
    public String deleteForm(@RequestParam(value = "id", required = true) String id) {
        long userId = Long.parseLong(id);
        userService.deleteById(userId);

        return "redirect:/admin/";
    }
}
