package com.hludencov.java_spring.controllers;

import com.hludencov.java_spring.repo.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hr")
@PreAuthorize("hasAuthority('HR') or hasAuthority('ADMISSION')")
public class HRController {

    @Autowired
    DocumentRepository documentRepository;


}
