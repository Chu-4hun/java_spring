package com.hludencov.java_spring.controllers;


import com.hludencov.java_spring.models.Movie;
import com.hludencov.java_spring.models.User;
import com.hludencov.java_spring.repo.MovieRepository;
import com.hludencov.java_spring.repo.PostRepository;
import com.hludencov.java_spring.repo.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.hludencov.java_spring.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class BlogController {

    @Autowired
    PostRepository postRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    UsersRepository usersRepository;

    @GetMapping("/blog")
    public String blogMain(Model model) {
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "blog/blog-main";
    }

    @GetMapping("/blog/add")
    public String blogAdd(Model model, Post post) {
        Iterable<User> users = usersRepository.findAll();
        Iterable<Movie> movie = movieRepository.findAll();
        model.addAttribute("users", users);
        model.addAttribute("movie", movie);
        return "blog/blog-add";
    }

     @PostMapping("/blog/add")
    public String moviePostAdd(@ModelAttribute("post") @Valid Post post, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "blog/blog-add";
        }
        postRepository.save(post);
        return "redirect:/blog";
    }

    @GetMapping("/blog/filter")
    public String blogFilter(Model model) {
        return "blog/blog-filter";
    }

    @PostMapping("/blog/filter/result")
    public String blogResult(@RequestParam String title, Model model) {
        List<Post> result = postRepository.findByTitleContains(title);
//        List<Post> result = postRepository.findLikeTitle(title);
        model.addAttribute("result", result);
        return "blog/blog-filter";
    }
    @GetMapping("/blog/show/{post}")
    public String postShow(
            Post post,
            Model model) {
        model.addAttribute("post", post);
        return "blog/blog-show";
    }


}
