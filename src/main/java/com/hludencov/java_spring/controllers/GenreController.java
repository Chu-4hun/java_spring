package com.hludencov.java_spring.controllers;


import com.hludencov.java_spring.models.Genre;
import com.hludencov.java_spring.models.Movie;
import com.hludencov.java_spring.repo.GenreRepository;
import com.hludencov.java_spring.repo.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("genre")
public class GenreController {
    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private MovieRepository movieRepository;

    @GetMapping()
    public String genreMain(Model model) {
        Iterable<Genre> genres = genreRepository.findAll();
        model.addAttribute("genres", genres);
        return "genre/genre-main";
    }

    @GetMapping("/add")
    public String genreAdd(Genre genre, Model model) {
        Iterable<Movie> movie = movieRepository.findAll();
        model.addAttribute("movie", movie);
        return "genre/genre-add";
    }

    @PostMapping("/add")
    public String genrePostAdd(
            @ModelAttribute("genre") @Valid Genre genre,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            Iterable<Movie> movie = movieRepository.findAll();
            model.addAttribute("movie", movie);
            return "genre/genre-add";
        }
        genreRepository.save(genre);
        return "redirect:";
    }

    @GetMapping("/edit/{genre}")
    public String genreEdit(Genre genre, Model model) {
        Iterable<Movie> movie = movieRepository.findAll();
        model.addAttribute("movie", movie);
        return "genre/genre-edit";
    }

    @PostMapping("/edit/{genre}")
    public String genrePostEdit(
            @ModelAttribute("genre") @Valid Genre genre,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            Iterable<Movie> movie = movieRepository.findAll();
            model.addAttribute("movie", movie);
            return "genre/genre-edit";
        }
        genreRepository.save(genre);
        return "redirect:../";
    }

    @GetMapping("/show/{genre}")
    public String genreShow(
            Genre genre, Model model) {
        model.addAttribute("genre", genre);
        return "genre/genre-show";
    }

    @GetMapping("/del/{genre}")
    public String genreDel(
            Genre genre) {
        genreRepository.delete(genre);
        return "redirect:../";
    }
}