package com.hludencov.java_spring.controllers;

import com.hludencov.java_spring.models.Movie;
import com.hludencov.java_spring.repo.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class MovieController {
    @Autowired
    MovieRepository movieRepository;

    @GetMapping("/movie")
    public String movieMain(Model model) {
        Iterable<Movie> movie = movieRepository.findAll();
        model.addAttribute("movie", movie);
        return "movie/movie-main";
    }

    @GetMapping("/movie/add")
    public String movieAdd(Movie movie) {
        return "movie/movie-add";
    }

    @PostMapping("/movie/add")
    public String moviePostAdd(@ModelAttribute("movie") @Valid Movie movie, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "movie/movie-add";
        }
        movieRepository.save(movie);
        return "redirect:/movie";
    }

    @GetMapping("/movie/edit/{movie}")
    public String movieEdit(
            Movie movie,
            Model model) {
        model.addAttribute("movie", movie);
        return "movie/movie-edit";
    }
    @PostMapping("/movie/edit/{movie}")
    public String moviePostEdit(@ModelAttribute("movie") @Valid Movie movie, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "movie/movie-edit";
        }
        movieRepository.save(movie);
        return "redirect:/movie";
    }

    @GetMapping("/movie/show/{movie}")
    public String movieShow(
            Movie movie,
            Model model) {
        model.addAttribute("movie", movie);
        return "movie/movie-show";
    }

    @GetMapping("/movie/del/{movie}")
    public String movieDel(
            Movie movie) {
        movieRepository.delete(movie);
        return "redirect:../";
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

    @PostMapping("/movie/filter_strict/result")
    public String blogStrictResult(@RequestParam String title, Model model) {
        List<Movie> result = movieRepository.findByName(title);
        model.addAttribute("result", result);
        return "movie/movie-filter";
    }
}
