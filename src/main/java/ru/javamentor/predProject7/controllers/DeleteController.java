package ru.javamentor.predProject7.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.javamentor.predProject7.repository.UserRepository;

@Controller
public class DeleteController {
    @Autowired
    UserRepository userRepository;

    @PostMapping("/del")
    public String delete(@RequestParam(value = "id") Long id) {
        userRepository.deleteById(id);

        return "redirect:/all";
    }
}
