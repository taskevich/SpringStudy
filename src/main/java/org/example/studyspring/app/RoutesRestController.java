package org.example.studyspring.app;

import org.example.studyspring.app.models.Users.*;
import org.example.studyspring.app.models.Roles.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class RoutesRestController {
    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private UsersRepository usersRepository;

    @GetMapping("/api")
    public DefaultResponse root(@RequestHeader MultiValueMap<String, String> headers) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("api", "ver 0.1");
        return new DefaultResponse(false, "OK", new Object[]{map});
    }

    @GetMapping("/api/users")
    public DefaultResponse getUsers() {
        Iterable<Users> users = usersRepository.findAll();
        return new DefaultResponse(false, "OK", new Object[]{users});
    }

    @PostMapping("/api/users")
    public DefaultResponse addUser(@RequestBody AddUserRequest newUser) {
        if (newUser.username().isEmpty() || newUser.password().isEmpty()) {
            return new DefaultResponse(true, "Пустые поля", null);
        }

        Users user = new Users();
        user.setUsername(newUser.username());
        user.setPassword(newUser.password());

        Optional<Roles> role = rolesRepository.findById(newUser.roleId());
        if (role.isPresent()) {
            user.setRole_id(role.get());
            usersRepository.save(user);
        } else {
            return new DefaultResponse(true, "Роль не существует", null);
        }

        return new DefaultResponse(false, "OK", null);
    }

    @DeleteMapping("/api/users")
    public DefaultResponse deleteUser(@RequestBody DeleteUserRequest deleteUser) {
        Optional<Users> user = usersRepository.findById(deleteUser.userId());

        if (user.isPresent()) {
            usersRepository.delete(user.get());
        } else {
            return new DefaultResponse(true, "Пользователь не найден", null);
        }

        return new DefaultResponse(false, "OK", null);
    }

    @GetMapping("/api/roles")
    public DefaultResponse getRoles() {
        Iterable<Roles> roles = rolesRepository.findAll();
        return new DefaultResponse(false, "OK", new Object[]{roles});
    }

    @PostMapping("/api/roles")
    public DefaultResponse addRole(@RequestBody AddRoleRequest newRole) {
        if (newRole.name().isEmpty()) {
            return new DefaultResponse(true, "У роли должно быть название", null);
        }

        Roles role = new Roles();
        role.setName(newRole.name());
        rolesRepository.save(role);
        return new DefaultResponse(false, "OK", new Object[]{role});
    }

    @DeleteMapping("/api/roles")
    public DefaultResponse deleteRole(@RequestBody DeleteRoleRequest deleteRole) {
        Optional<Roles> role = rolesRepository.findById(deleteRole.roleId());

        if (role.isPresent()) {
            rolesRepository.delete(role.get());
        } else {
            return new DefaultResponse(true, "Роль не найдена", null);
        }

        return new DefaultResponse(false, "OK", null);
    }
}
