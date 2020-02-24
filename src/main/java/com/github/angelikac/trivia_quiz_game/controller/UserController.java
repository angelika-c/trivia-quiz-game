package com.github.angelikac.trivia_quiz_game.controller;

import com.github.angelikac.trivia_quiz_game.dto.UserDto;
import com.github.angelikac.trivia_quiz_game.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginForm(){
        return "login";
    }

    @GetMapping("/register")
    public String registerForm() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") UserDto user, BindingResult errors) {
        if (errors.hasErrors()) {
            return "register";
        }

        userService.saveUser(user);
        return "redirect:/login";
    }

    @ModelAttribute("user")
    public UserDto user() {
        return new UserDto();
    }
}
