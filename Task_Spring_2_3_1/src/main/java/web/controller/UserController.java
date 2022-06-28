package web.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.User;
import web.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    
    @GetMapping
    public String list(ModelMap model) {
        List<User> listUsers = userService.findAll();
        model.addAttribute("users", listUsers);
        return "userslist";
    }
    
    @GetMapping(value = "/update")
    public String newForm(@RequestParam(value = "id", required = false) String id, ModelMap model) {
        User user = new User();
        if (id != null) {
            long userId = Long.parseLong(id);
            user = userService.findById(userId).get();
        }
        model.addAttribute("user", user);
        return "usersEdit";
    }
    @PostMapping(value = "/update")
    public String updateForm(@ModelAttribute User user, ModelMap model) {
        model.addAttribute("user", user);
        userService.save(user);
        
        return "redirect:/users";
    }
    @GetMapping(value = "/delete")
    public String deleteForm(@RequestParam(value = "id", required = false) String id) {
        if (id != null) {
            long userId = Long.parseLong(id);
            userService.deleteById(userId);
        }
        return "redirect:/users";
    }
}
