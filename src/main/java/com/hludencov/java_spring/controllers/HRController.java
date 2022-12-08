package com.hludencov.java_spring.controllers;

import com.hludencov.java_spring.models.Document;
import com.hludencov.java_spring.models.Role;
import com.hludencov.java_spring.models.Summary;
import com.hludencov.java_spring.models.User;
import com.hludencov.java_spring.repo.DocumentRepository;
import com.hludencov.java_spring.repo.SummaryRepository;
import com.hludencov.java_spring.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Scanner;

@Controller
@RequestMapping("/hr")
@PreAuthorize("hasAnyAuthority('HR','ADMISSION')")
public class HRController {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    DocumentRepository documentRepository;
    @Autowired
    SummaryRepository summaryRepository;
    @Autowired
    SummaryRepository subjectRepository;



    User user = new User();

    @GetMapping
    public String documentList(Model model) {
        user = userRepository.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("user", user);
        if (user.getRoles().contains(Role.ADMISSION)) {
            model.addAttribute("documents", documentRepository.findByToAdmission(true));
        } else {
            model.addAttribute("documents", documentRepository.findByToAdmission(false));
        }
        return "hr/hr-main";
    }
    @GetMapping("/editor/{document}")
    public String openEditor(Model model, @PathVariable Document document){
        model.addAttribute("summaries", summaryRepository.findByDocument(document));
        model.addAttribute("summary", new Summary());
         model.addAttribute("subjects", subjectRepository.findAll());
        model.addAttribute("username", document.user.getPersonal_info().getName());

        return "hr/hr-editor";
    }


}
