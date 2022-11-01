package com.hludencov.java_spring.controllers;


import com.hludencov.java_spring.models.Group;
import com.hludencov.java_spring.repo.GroupRepository;
import com.hludencov.java_spring.repo.Preparation_programRepository;
import com.hludencov.java_spring.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/group")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class GroupController {
    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Preparation_programRepository preparation_programRepository;

    @GetMapping
    public String educationList(Group group, Model model) {
        model.addAttribute("group", groupRepository.findAll());
        return "group/group-main";
    }


    @GetMapping("/add")
    public String educationAdd(Group group, Model model) {
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("preparation_programs", preparation_programRepository.findAll());
        return "group/group-add";
    }

    @PostMapping("/add")
    public String educationPostAdd(@ModelAttribute("group") @Valid Group group, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("users", userRepository.findAll());
            model.addAttribute("preparation_programs", preparation_programRepository.findAll());
            return "group/group-add";
        }
        groupRepository.save(group);
        return "redirect:/group";
    }


    @GetMapping("/edit/{group}")
    public String educationEdit(
            Group group,
            Model model) {
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("preparation_programs", preparation_programRepository.findAll());
        model.addAttribute("group", group);
        return "group/group-edit";
    }

    @PostMapping("/edit/{group}")
    public String educationPostEdit(@ModelAttribute("group") @Valid Group group, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("users", userRepository.findAll());
            model.addAttribute("preparation_programs", preparation_programRepository.findAll());
            return "group/group-edit";
        }
        groupRepository.save(group);
        return "redirect:../";
    }

    @GetMapping("/show/{group}")
    public String educationShow(
            Group group,
            Model model) {
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("preparation_programs", preparation_programRepository.findAll());
        model.addAttribute("groups", group);
        return "group/group-show";
    }

    @GetMapping("/del/{group}")
    public String educationDel(
            Group group) {
        groupRepository.delete(group);
        return "redirect:../";
    }

    @GetMapping("/filter")
    public String educationFilter(Model model) {
        return "group/group-filter";
    }

}
