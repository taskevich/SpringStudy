package org.example.studyspring.app;

import org.example.studyspring.app.Users.Users;
import org.example.studyspring.app.Users.UsersRepository;
import org.example.studyspring.app.models.Roles.Roles;
import org.example.studyspring.app.models.Roles.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class RoutesController {
    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private UsersRepository usersRepository;

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/users")
    public String users(Model model) {
        Iterable<Users> users = usersRepository.findAll();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/roles")
    public String roles(Model model) {
        Iterable<Roles> roles = rolesRepository.findAll();
        model.addAttribute("roles", roles);
        return "roles";
    }
}
