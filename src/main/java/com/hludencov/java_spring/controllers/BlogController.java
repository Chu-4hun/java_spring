package com.hludencov.java_spring.controllers;


import com.hludencov.java_spring.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.hludencov.java_spring.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class BlogController  {

    @Autowired
    PostRepository postRepository;

    @GetMapping("/blog")
    public String blogMain(Model model)
    {
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "blog/blog-main";
    }

   @GetMapping("/blog/add")
    public String blogAdd(Model model)
    {
        return "blog/blog-add";
    }

    @PostMapping("/blog/add")
    public String blogPostAdd(@RequestParam String title,
                              @RequestParam String anons,
                              @RequestParam String full_text, Model model)
    {
        Post post = new Post(title, anons, full_text);
        postRepository.save(post);
        return "redirect:/";
    }

    @GetMapping("/blog/filter")
    public String blogFilter(Model model)
    {
        return "blog/blog-filter";
    }

    @PostMapping("/blog/filter/result")
    public String blogResult(@RequestParam String title, Model model)
    {
        List<Post> result = postRepository.findByTitleContains(title);
//        List<Post> result = postRepository.findLikeTitle(title);
        model.addAttribute("result", result);
        return "blog/blog-filter";
    }


}
