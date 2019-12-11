package ru.javamentor.predProject7.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.javamentor.predProject7.entities.User;
import ru.javamentor.predProject7.exception.DBException;
import ru.javamentor.predProject7.service.UserService;

@Controller
public class AddController {
    @Autowired
    UserService userService;

    @PostMapping("/add")
    public String add(@RequestParam String username, @RequestParam String password, @RequestParam Integer age, @RequestParam(required = false) String role, Model model) throws DBException {
        if (userService.isExistsUser(username)) {
            model.addAttribute("message", "null");
            return "redirect:/admin";
        }

        userService.addUser(new User(username, password, age, role));

        return "redirect:/admin";
    }
}
