package com.hludencov.java_spring.controllers;


import com.hludencov.java_spring.models.Preparation_program;
import com.hludencov.java_spring.repo.Preparation_programRepository;
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
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class Preparation_programController {
    
    @Autowired
    private Preparation_programRepository preparation_programRepository;

    @GetMapping
    public String educationList(Preparation_program preparation_program, Model model){
        model.addAttribute("preparation_program", preparation_programRepository.findAll());
        return "preparation_program/preparation_program-main";
    }


    @GetMapping("/add")
    public String educationAdd(Preparation_program preparation_program) {
        return "preparation_program/preparation_program-add";
    }

    @PostMapping("/add")
    public String educationPostAdd(@ModelAttribute("preparation_program") @Valid Preparation_program preparation_program, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "preparation_program/preparation_program-add";
        }
        preparation_programRepository.save(preparation_program);
        return "redirect:/preparation_program";
    }


    @GetMapping("/edit/{preparation_program}")
    public String educationEdit(
            Preparation_program preparation_program,
            Model model) {
        model.addAttribute("preparation_program", preparation_program);
        return "preparation_program/preparation_program-edit";
    }

    @PostMapping("/edit/{preparation_program}")
    public String educationPostEdit(@ModelAttribute("preparation_program") @Valid Preparation_program preparation_program, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "preparation_program/preparation_program-edit";
        }
        preparation_programRepository.save(preparation_program);
        return "redirect:../";
    }

    @GetMapping("/show/{preparation_program}")
    public String educationShow(
            Preparation_program preparation_program,
            Model model) {
        model.addAttribute("preparation_program", preparation_program);
        return "preparation_program/preparation_program-show";
    }

    @GetMapping("/del/{preparation_program}")
    public String educationDel(
            Preparation_program preparation_program) {
        preparation_programRepository.delete(preparation_program);
        return "redirect:../";
    }

    @GetMapping("/filter")
    public String educationFilter(Model model) {
        return "preparation_program/preparation_program-filter";
    }

    @PostMapping("/filter/result")
    public String educationResult(@RequestParam String title, Model model) {
        List<Preparation_program> result = preparation_programRepository.findByNameContains(title);
        model.addAttribute("result", result);
        return "preparation_program/preparation_program-filter";
    }

    @PostMapping("/filter_strict/result")
    public String educationStrictResult(@RequestParam String title, Model model) {
        List<Preparation_program> result = preparation_programRepository.findByName(title);
        model.addAttribute("result", result);
        return "preparation_program/preparation_program-filter";
    }
}
