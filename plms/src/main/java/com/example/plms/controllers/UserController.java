package com.example.plms.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/user_dashboard")
    public String showUserDashboard(Model model) {
        return "user_dashboard"; // Returns the User Dashboard
    }
}
