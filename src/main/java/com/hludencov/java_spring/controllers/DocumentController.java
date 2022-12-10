package com.hludencov.java_spring.controllers;


import com.hludencov.java_spring.models.Document;
import com.hludencov.java_spring.models.Document_status;
import com.hludencov.java_spring.models.User;
import com.hludencov.java_spring.repo.DocumentRepository;
import com.hludencov.java_spring.repo.UserRepository;
import com.hludencov.java_spring.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.validation.Valid;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@Controller
@RequestMapping("/document")
public class DocumentController {
    private final StorageService storageService;

    @Autowired
    public DocumentController(StorageService storageService) {
        this.storageService = storageService;
    }

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private UserRepository userRepository;

    User user = new User();

    @GetMapping
    public String documentList(Model model) {
        user = userRepository.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("user", user);
        model.addAttribute("documents", documentRepository.findByUser_id(user.getId()));
        return "document/document-main";
    }

    @GetMapping("/add")
    public String documentAdd(Document document, Model model) {
        model.addAttribute("documents", documentRepository.findAll());
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("user", user);
        return "document/document-add";
    }

    @PostMapping("/add")
    public String documentPostAdd(@ModelAttribute("document") @Valid Document document, BindingResult bindingResult,
                                  Model model, @RequestParam("file") MultipartFile file) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("documents", documentRepository.findAll());
            model.addAttribute("users", userRepository.findAll());
            return "document/document-add";
        }

        Date uploadDate = new Date(System.currentTimeMillis());
        document.date = uploadDate;
        document.fileName = file.getOriginalFilename();
        document.archive_date = Date.valueOf(uploadDate.toLocalDate().plusMonths(1));
        document.user = user;

        document.status = Document_status.PENDING;
        documentRepository.save(document);
        storageService.store(file);
        return "redirect:/candidate/add";
    }

    @GetMapping("/edit/{document}")
    public String documentEdit(
            Document document,
            Model model) {
        model.addAttribute("documents", document);
        return "document/document-edit";
    }

    @PostMapping("/edit/{document}")
    public String documentPostEdit(@ModelAttribute("document") @Valid Document document, BindingResult bindingResult,
                                   Model model, @RequestParam("file") MultipartFile file) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("documents", documentRepository.findAll());
            return "document/document-edit";
        }
        document.fileName = file.getOriginalFilename();
        storageService.store(file);
        documentRepository.save(document);
        return "redirect:../";
    }

    @GetMapping("/show/{document}")
    public String documentShow(
            Document document,
            Model model) throws IOException {
        model.addAttribute("path", storageService.loadAsResource(document.fileName).getURI().toString());
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

    //    @GetMapping("/")
//	public String listUploadedFiles(Model model) throws IOException {
//
//		model.addAttribute("files", storageService.loadAll().map(
//				path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
//						"serveFile", path.getFileName().toString()).build().toUri().toString())
//				.collect(Collectors.toList()));
//
//		return "uploadForm";
//	}
//
    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
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
