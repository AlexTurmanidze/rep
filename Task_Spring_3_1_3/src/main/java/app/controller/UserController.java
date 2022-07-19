package app.controller;

import app.service.UserService;
import app.model.User;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
            
    @GetMapping
    public String userPage(Principal principal, ModelMap model) {
        User user = userService.findByEmail(principal.getName()).get();
        model.addAttribute("user", user);
        return "user";
    }
}
