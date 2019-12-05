package ru.javamentor.predProject7.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.javamentor.predProject7.entities.User;
import ru.javamentor.predProject7.repository.UserRepository;

@Controller
public class AddController {
    @Autowired
    UserRepository userRepository;

    @PostMapping("/add")
    public String add(@RequestParam String username, @RequestParam String password, @RequestParam Integer age, @RequestParam String role, Model model) {
        if (StringUtils.isEmpty(age)) {
            age = 0;
        }

        if (StringUtils.isEmpty(role)) {
            role = "user";
        }

        userRepository.save(new User(username, password, age, role));

        return "redirect:/all";
    }
}
