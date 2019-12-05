package ru.javamentor.predProject7.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    @GetMapping("/")
    public String greeting(@RequestParam(name = "myName", required = false, defaultValue = "Nick")String myName, Model model) {
        model.addAttribute("myName", myName);

        return "index";
    }
}
