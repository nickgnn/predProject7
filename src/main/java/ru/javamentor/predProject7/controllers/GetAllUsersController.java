package ru.javamentor.predProject7.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.javamentor.predProject7.exception.DBException;
import ru.javamentor.predProject7.service.UserService;

@Controller
public class GetAllUsersController {
    @Autowired
    UserService userService;

    @GetMapping("/all")
    public String getAllUsers(Model model) throws DBException {
        model.addAttribute("users", userService.getAllUsers());

        return "allUsers";
    }
}
