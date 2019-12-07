package ru.javamentor.predProject7.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    public String add(@RequestParam String username, @RequestParam String password, @RequestParam Integer age, @RequestParam String role) throws DBException {
        userService.addUser(new User(username, password, age, role));

        return "redirect:/all";
    }
}
