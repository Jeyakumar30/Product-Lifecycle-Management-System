package com.example.plms.controllers;

import com.example.plms.services.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        Map<String, Long> productCountByStage = dashboardService.getProductCountByStage();

        model.addAttribute("productCountByStage", productCountByStage);

        return "dashboard";
    }
}