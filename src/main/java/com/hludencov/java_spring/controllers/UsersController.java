package com.hludencov.java_spring.controllers;


import com.hludencov.java_spring.models.User;
import com.hludencov.java_spring.repo.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UsersController {
    @Autowired
    UsersRepository usersRepository;

    @GetMapping("/user")
    public String userMain(Model model) {
        Iterable<User> users = usersRepository.findAll();
        model.addAttribute("users", users);
        return "user/user-main";
    }

    @GetMapping("/user/add")
    public String userAdd(Model model)
    {
        return "user/user-add";
    }
    @PostMapping("/user/add")
    public String userPostAdd(@RequestParam String nickname,
                              @RequestParam String birthdate,
                              @RequestParam String register_date,
                              @RequestParam int views,
                              @RequestParam int friends,
                              Model model) {
        User user = new User(nickname,
                birthdate,
                register_date, views,
                friends);
        usersRepository.save(user);
        return "redirect:/user";
    }

    @GetMapping("/user/filter")
    public String userFilter(Model model) {
        return "user/user-filter";
    }

    @PostMapping("/user/filter/result")
    public String userResult(@RequestParam String title, Model model) {
        List<User> result = usersRepository.findByNicknameContains(title);
        model.addAttribute("result", result);
        return "user/user-filter";
    }
}
