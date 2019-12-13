package ru.javamentor.predProject7.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.javamentor.predProject7.exception.DBException;
import ru.javamentor.predProject7.service.UserService;

@Controller
public class DeleteController {
    @Autowired
    UserService userService;

    @PostMapping("/del")
    public String delete(@RequestParam(value = "id") Long id) throws DBException {
        userService.deleteUserById(id);

        return "redirect:/all";
    }
}
