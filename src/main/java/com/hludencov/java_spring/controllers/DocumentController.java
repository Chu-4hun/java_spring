package com.hludencov.java_spring.controllers;


import com.hludencov.java_spring.models.Document;
import com.hludencov.java_spring.repo.DocumentRepository;
import com.hludencov.java_spring.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/document")
public class DocumentController {

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String documentList(Model model) {
        model.addAttribute("documents", documentRepository.findAll());
        return "document/document-main";
    }

    @GetMapping("/add")
    public String documentAdd(Document document, Model model) {
        model.addAttribute("documents", documentRepository.findAll());
        model.addAttribute("users", userRepository.findAll());
        return "document/document-add";
    }

    @PostMapping("/add")
    public String documentPostAdd(@ModelAttribute("document") @Valid Document document, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("documents", documentRepository.findAll());
            model.addAttribute("users", userRepository.findAll());
            return "document/document-add";
        }
        documentRepository.save(document);
        return "redirect:/document";
    }


    @GetMapping("/edit/{document}")
    public String documentEdit(
            Document document,
            Model model) {
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("documents", document);
        return "document/document-edit";
    }

    @PostMapping("/edit/{document}")
    public String documentPostEdit(@ModelAttribute("document") @Valid Document document, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("documents", documentRepository.findAll());
            model.addAttribute("users", userRepository.findAll());
            return "document/document-edit";
        }
        documentRepository.save(document);
        return "redirect:../";
    }

    @GetMapping("/show/{document}")
    public String documentShow(
            Document document,
            Model model) {
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("documents", document);
        return "document/document-show";
    }

    @GetMapping("/del/{document}")
    public String documentDel(
            Document document) {
        documentRepository.delete(document);
        return "redirect:../";
    }

    @GetMapping("/filter")
    public String documentFilter(Model model) {
        return "document/document-filter";
    }

    @PostMapping("/filter/result")
    public String documentResult(@RequestParam String title, Model model) {
        List<Document> result = documentRepository.findByFileNameContains(title);
        model.addAttribute("result", result);
        return "document/document-filter";
    }

    @PostMapping("/filter_strict/result")
    public String documentStrictResult(@RequestParam String title, Model model) {
        List<Document> result = documentRepository.findByFileName(title);
        model.addAttribute("result", result);
        return "document/document-filter";
    }
}
