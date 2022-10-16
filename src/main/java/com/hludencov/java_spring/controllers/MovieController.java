package com.hludencov.java_spring.controllers;

import com.hludencov.java_spring.models.Movie;
import com.hludencov.java_spring.repo.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class MovieController {
    @Autowired
    MovieRepository movieRepository;

    @GetMapping("/movie")
    public String blogMain(Model model) {
        Iterable<Movie> movie = movieRepository.findAll();
        model.addAttribute("movie", movie);
        return "movie/movie-main";
    }

    @GetMapping("/movie/add")
    public String blogAdd(Model model) {
        return "movie/movie-add";
    }

    @PostMapping("/movie/add")
    public String blogPostAdd(@RequestParam String name,
                              @RequestParam String release_date,
                              @RequestParam double rating,
                              @RequestParam int comments_amount,
                              @RequestParam int series_amount) {

        Movie movie = new Movie(name, release_date, rating, series_amount, comments_amount);
        movieRepository.save(movie);
        return "redirect:/movie";
    }

    @GetMapping("/movie/filter")
    public String blogFilter(Model model) {
        return "movie/movie-filter";
    }

    @PostMapping("/movie/filter/result")
    public String blogResult(@RequestParam String title, Model model) {
        List<Movie> result = movieRepository.findByNameContains(title);
        model.addAttribute("result", result);
        return "movie/movie-filter";
    }
}
