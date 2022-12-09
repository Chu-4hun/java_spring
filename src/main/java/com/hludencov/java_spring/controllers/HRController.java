package com.hludencov.java_spring.controllers;

import com.hludencov.java_spring.models.Document;
import com.hludencov.java_spring.models.Role;
import com.hludencov.java_spring.models.Summary;
import com.hludencov.java_spring.models.User;
import com.hludencov.java_spring.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    SubjectRepository subjectRepository;
    @Autowired
    CandidateRepository candidateRepository;


    User user = new User();
    Document doc;


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
    public String openEditor(Model model, @PathVariable Document document) {
        model.addAttribute("summaries", summaryRepository.findByDocument(document));
        model.addAttribute("summary", new Summary());
        model.addAttribute("subjects", subjectRepository.findAll());
        model.addAttribute("username", document.user.getPersonal_info().getName());
        doc = document;

        return "hr/hr-editor";
    }
    @PostMapping("/editor/add")
    public String summaryPostAdd(@ModelAttribute("summary") @Valid Summary summary, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("candidate", candidateRepository.findAll());
            model.addAttribute("subjects", subjectRepository.findAll());
            return "summary/summary-add";
        }
        summary.setDocument(doc);
        summary.setCandidate_info(doc.user.getCandidate_info());

        summaryRepository.save(summary);
        return "redirect:/hr/editor/"+ doc.id;
    }


}
