package com.hludencov.java_spring.controllers;


import com.hludencov.java_spring.models.Candidate_info;
import com.hludencov.java_spring.models.User;
import com.hludencov.java_spring.repo.CandidateRepository;
import com.hludencov.java_spring.repo.DepartmentRepository;
import com.hludencov.java_spring.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Date;
import java.util.List;

@PreAuthorize("hasAuthority('TEACHER') or hasAuthority('STUDENT') or hasAuthority('USER')")
@Controller
@RequestMapping("/candidate")
public class CandidateInfoController {
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private UserRepository userRepository;
     User user = new User();

    @GetMapping
    public String candidateList(Model model) {
        model.addAttribute("candidates", candidateRepository.findAll());
        user = userRepository.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        return "candidate/candidate-main";
    }

    @GetMapping("/add")
    public String candidateAdd(Candidate_info candidate, Model model) {
        model.addAttribute("candidates", candidateRepository.findAll());
        model.addAttribute("departments", departmentRepository.findAll());
        model.addAttribute("users", userRepository.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()));
        return "candidate/candidate-add";
    }

    @PostMapping("/add")
    public String candidatePostAdd(@ModelAttribute("candidate_info") @Valid Candidate_info candidate, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("candidates", candidateRepository.findAll());
            model.addAttribute("departments", departmentRepository.findAll());
            return "candidate/candidate-add";
        }
        candidate.user = user;
        candidate.submissionDate = new Date(System.currentTimeMillis());
        candidateRepository.save(candidate);
        return "redirect:/candidate";
    }


    @GetMapping("/edit/{candidate_info}")
    public String candidateEdit(
            Candidate_info candidate,
            Model model) {
        model.addAttribute("departments", departmentRepository.findAll());
        model.addAttribute("candidates", candidate);
        return "candidate/candidate-edit";
    }

    @PostMapping("/edit/{candidate_info}")
    public String candidatePostEdit(@ModelAttribute("candidate_info") @Valid Candidate_info candidate, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("candidates", candidateRepository.findAll());
            model.addAttribute("departments", departmentRepository.findAll());
            return "candidate/candidate-edit";
        }
        candidateRepository.save(candidate);
        return "redirect:../";
    }

    @GetMapping("/show/{candidate_info}")
    public String candidateShow(
            Candidate_info candidate,
            Model model) {
        model.addAttribute("departments", departmentRepository.findAll());
        model.addAttribute("candidates", candidate);
        return "candidate/candidate-show";
    }

    @GetMapping("/del/{candidate_info}")
    public String candidateDel(
            Candidate_info candidate) {
        candidateRepository.delete(candidate);
        return "redirect:../";
    }

    @GetMapping("/filter")
    public String candidateFilter(Model model) {
        return "candidate/candidate-filter";
    }

    @PostMapping("/filter/result")
    public String candidateResult(@RequestParam String title, Model model) {
        List<Candidate_info> result = candidateRepository.findBySubmissionDateContains(title);
        model.addAttribute("result", result);
        return "candidate/candidate-filter";
    }
}
