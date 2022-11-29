package com.hludencov.java_spring.controllers;


import com.hludencov.java_spring.models.Preparation_program;
import com.hludencov.java_spring.repo.Preparation_programRepository;
import com.hludencov.java_spring.repo.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/preparation_program")
@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('TEACHER')")
public class Preparation_programController {
    
    @Autowired
    private Preparation_programRepository preparation_programRepository;

    @Autowired
    private SubjectRepository subjectRepository;


    @GetMapping
    public String prepprogList(Preparation_program preparation_program, Model model){
        model.addAttribute("preparation_program", preparation_programRepository.findAll());
        return "preparation_program/preparation_program-main";
    }


    @GetMapping("/add")
    public String prepprogAdd(Preparation_program preparation_program,Model model) {
        model.addAttribute("subjectSet", subjectRepository.findAll());
        return "preparation_program/preparation_program-add";
    }

    @PostMapping("/add")
    public String prepprogPostAdd(@ModelAttribute("preparation_program") @Valid Preparation_program preparation_program, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("subjectSet", subjectRepository.findAll());
            return "preparation_program/preparation_program-add";
        }
        preparation_programRepository.save(preparation_program);
        return "redirect:/preparation_program";
    }


    @GetMapping("/edit/{preparation_program}")
    public String prepprogEdit(
            Preparation_program preparation_program,
            Model model) {
        model.addAttribute("subjectSet", subjectRepository.findAll());
        model.addAttribute("preparation_program", preparation_program);
        return "preparation_program/preparation_program-edit";
    }

    @PostMapping("/edit/{preparation_program}")
    public String prepprogPostEdit(@ModelAttribute("preparation_program") @Valid Preparation_program preparation_program, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("subjectSet", subjectRepository.findAll());
            return "preparation_program/preparation_program-edit";
        }
        preparation_programRepository.save(preparation_program);
        return "redirect:../";
    }

    @GetMapping("/show/{preparation_program}")
    public String prepprogShow(
            Preparation_program preparation_program,
            Model model) {
        model.addAttribute("preparation_program", preparation_program);
        return "preparation_program/preparation_program-show";
    }

    @GetMapping("/del/{preparation_program}")
    public String prepprogDel(
            Preparation_program preparation_program) {
        preparation_programRepository.delete(preparation_program);
        return "redirect:../";
    }

    @GetMapping("/filter")
    public String prepprogFilter(Model model) {
        return "preparation_program/preparation_program-filter";
    }

    @PostMapping("/filter/result")
    public String prepprogResult(@RequestParam String title, Model model) {
        List<Preparation_program> result = preparation_programRepository.findByNameContains(title);
        model.addAttribute("result", result);
        return "preparation_program/preparation_program-filter";
    }

    @PostMapping("/filter_strict/result")
    public String prepprogStrictResult(@RequestParam String title, Model model) {
        List<Preparation_program> result = preparation_programRepository.findByName(title);
        model.addAttribute("result", result);
        return "preparation_program/preparation_program-filter";
    }
}
