package com.example.plms.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReportPageController {

    @GetMapping("/lifecycle-reports")
    public String showLifecycleReportsPage() {
        return "lifecycle_reports";
    }
}