package ru.javamentor.predProject7.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.javamentor.predProject7.entities.Role;
import ru.javamentor.predProject7.entities.User;
import ru.javamentor.predProject7.exception.DBException;
import ru.javamentor.predProject7.service.UserService;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@Controller
public class GetAllUsersController {
    @Autowired
    UserService userService;

    @GetMapping("/all")
    public String getAllUsers(Model model) throws DBException {
        model.addAttribute("users", userService.getAllUsers());

        return "userList";
    }

    @GetMapping("/all/{id}")
    public String editUserView(@PathVariable("id") Long id, Model model) throws DBException {
        model.addAttribute("user", userService.getUserById(id));

        return "userEdit";
    }

    @PostMapping("/all/{id}")
    public String editUser(@PathVariable("id") Long id, @RequestParam String username, @RequestParam String password, @RequestParam Integer age, @RequestParam String role, Model model) throws DBException {
        User userEdited = new User(id, username, password, age, role);
        User userById = userService.getUserById(id);

        if (userService.isExistsUser(userEdited.getUsername())) {
            if (userService.getUserByName(username).getId() != userEdited.getId()) {
                return "redirect:/all/{id}";
            }
        }

        userById.setPassword(password);
        userById.setAge(age);
        userById.setRole(role);

        userService.editUser(userById);

        return "redirect:/all";
    }
}
