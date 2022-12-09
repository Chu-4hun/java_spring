package com.hludencov.java_spring.controllers;

import com.hludencov.java_spring.models.*;
import com.hludencov.java_spring.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

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
        var summaries = summaryRepository.findByDocument(document);
        var subjects = subjectRepository.findAll();
        var subjects_checked = new HashSet<Subject>();

        for (Subject subject : subjects) {
            boolean r = false;
            for (Summary summary : summaries) {
                if (Objects.equals(summary.getSubject().getId(), subject.getId())) {
                    r = true;
                    break;
                }
            }
            if (!r) {
                subjects_checked.add(subject);
            }
        }

        model.addAttribute("summaries", summaries);
        model.addAttribute("document", document);
        model.addAttribute("summary", new Summary());
        model.addAttribute("subjects", subjects_checked);
        model.addAttribute("username", document.user.getPersonal_info().getName());
        doc = document;


        List<Integer> marks = new ArrayList<Integer>();
        for (var sum : summaries) {
            marks.add(sum.getMark());
        }

        List<String> sub_names = new ArrayList<String>();
        for (var sum : summaries) {
            sub_names.add(sum.getSubject().getName());
        }
        int totalSum = 0;
        for (Integer mark : marks) {
            totalSum += mark;
        }
        double average = (double) totalSum / marks.size();

        model.addAttribute("subjects_name", sub_names);
        model.addAttribute("marks", marks);
        model.addAttribute("marks_average", String.format("%.2f", average));
        if (average > 1) {
            document.averageMark = average;
        }
        documentRepository.save(document);
        return "hr/hr-editor";
    }


    @PostMapping("/editor/add")
    public String editorPostAdd(@ModelAttribute("summary") @Valid Summary summary, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("candidate", candidateRepository.findAll());
            model.addAttribute("subjects", subjectRepository.findAll());
            return "summary/summary-add";
        }
        summary.setDocument(doc);
        summary.setCandidate_info(doc.user.getCandidate_info());

        summaryRepository.save(summary);
        return "redirect:/hr/editor/" + doc.id;
    }

    @GetMapping("/editor/{document}/export")
    public void editorExelExport(HttpServletResponse response, @PathVariable Document document) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=SummaryExportOf_" + document.user.getPersonal_info().getName() + "_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        ExelExport exelExport = new ExelExport(summaryRepository.findByDocument(document), document.getUser().getPersonal_info());
        exelExport.generateExcelFile(response);
    }

    @GetMapping(value = "/editor/update", params = {"mark", "summary"})
    public String editorUpdate(@RequestParam(value = "mark") int mark, @RequestParam(value = "summary") Summary summary) {
        summary.setMark(mark);
        summaryRepository.save(summary);
        return "redirect:/hr/editor/" + doc.id;
    }


}
