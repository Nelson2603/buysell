package com.example.buysell.controller;

import com.example.buysell.models.User;
import com.example.buysell.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @GetMapping("/registration")

    public String registration() {
        return "registration";
    }


    @PostMapping("/registration")
    public String createUser( User user, Model model) {
        if (!userService.createUser(user)) {
            model.addAttribute("errorMessage", "пользователь с email: " + user.getEmail() + " уже существует");
            return "registration";
        }
        return "redirect:/login";
    }

//    @GetMapping("/hello")
//    public String hello() {
//        return "hello";
//
   @GetMapping("/products")
    public String products() {
        return "products";
    }

}
