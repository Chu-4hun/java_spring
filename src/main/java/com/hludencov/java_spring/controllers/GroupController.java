package com.hludencov.java_spring.controllers;


import com.google.common.collect.Iterables;
import com.hludencov.java_spring.models.Group;
import com.hludencov.java_spring.repo.GroupRepository;
import com.hludencov.java_spring.repo.PreparationProgramRepository;
import com.hludencov.java_spring.repo.UsersRepository;
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
import java.util.Calendar;

@Controller
@RequestMapping("/group")
@PreAuthorize("hasAnyAuthority('ADMIN') or hasAuthority('TEACHER')")
public class GroupController {
    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PreparationProgramRepository preparation_programRepository;

    @GetMapping
    public String educationList(Group group, Model model) {
        model.addAttribute("group", groupRepository.findAll());
        return "group/group-main";
    }


    @GetMapping("/add")
    public String educationAdd(Group group, Model model) {
        model.addAttribute("users", usersRepository.findAll());
        model.addAttribute("preparation_programs", preparation_programRepository.findAll());
        return "group/group-add";
    }

    @PostMapping("/add")
    public String educationPostAdd(@ModelAttribute("group") @Valid Group group, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("users", usersRepository.findAll());
            model.addAttribute("preparation_programs", preparation_programRepository.findAll());
            return "group/group-add";
        }
        group.setName(generateName(group));
        groupRepository.save(group);
        return "redirect:/group";
    }



    @GetMapping("/edit/{group}")
    public String educationEdit(
            Group group,
            Model model) {
        model.addAttribute("users", usersRepository.findAll());
        model.addAttribute("preparation_programs", preparation_programRepository.findAll());
        model.addAttribute("group", group);
        return "group/group-edit";
    }

    @PostMapping("/edit/{group}")
    public String educationPostEdit(@ModelAttribute("group") @Valid Group group, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("users", usersRepository.findAll());
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
        model.addAttribute("users", usersRepository.findByGroupsContaining(group));
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

    //_________________________________UTILS_____________________________________
    private String generateName(Group group) {
        return group.getPreparationProgram().getName() + "-"
                + (Iterables.size(groupRepository.findByPreparationProgram(group.getPreparationProgram())) + 1) + "-"
                + (Calendar.getInstance().get(Calendar.YEAR) - 2000);
    }

}
