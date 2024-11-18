package com.example.plms.controllers;

import com.example.plms.services.StageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class AdminController {

    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "password";



    @GetMapping("/login_page")
    public String showLoginPage() {
        return "login_page";
    }

    @GetMapping("/admin/dashboard")
    public String adminDashboard(Model model) {
        return "admin_dashboard";
    }

    @PostMapping("/admin/login")
    public String login(@RequestParam String username, @RequestParam String password, RedirectAttributes redirectAttributes) {
        System.out.println("ADMIN_USERNAME "+ ADMIN_USERNAME);
        System.out.println("ADMIN_PASSWORD "+ ADMIN_PASSWORD);

        System.out.println("username "+ username);
        System.out.println("password "+ password);

        if (ADMIN_USERNAME.equals(username) && ADMIN_PASSWORD.equals(password)) {
            return "redirect:/dashboard";
        } else {
            redirectAttributes.addFlashAttribute("error", "Invalid credentials!");
            return "redirect:/login";
        }
    }

    @Autowired
    private StageService stageService;

    // Admin reports endpoint
    @GetMapping("/admin/reports")
    public String showReportsPage(Model model) {
        // Fetch all stages dynamically
        List<String> lifecycleStages = stageService.getAllStages();
        model.addAttribute("stages", lifecycleStages);

        return "admin_reports";
    }
}
