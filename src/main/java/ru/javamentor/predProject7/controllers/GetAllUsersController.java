package ru.javamentor.predProject7.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.javamentor.predProject7.repository.UserRepository;

@Controller
public class GetAllUsersController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/all")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());

        return "allUsers";
    }
}
