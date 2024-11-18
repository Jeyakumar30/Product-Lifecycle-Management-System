package com.example.plms.controllers;

import com.example.plms.models.Product;
import com.example.plms.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserDashboardController {

    @Autowired
    private ProductService productService;

    @GetMapping("/dashboard")
    public String showUserDashboard(Model model) {
        List<Product> products = productService.getProductsWithLifecycleStages();
        Map<String, Long> lifecycleCounts = productService.getLifecycleCounts();

        model.addAttribute("products", products);
        model.addAttribute("lifecycleCounts", lifecycleCounts);

        return "user_dashboard"; // Thymeleaf template name

        //    @GetMapping("/dashboard")
//    public String showUserDashboard(Model model) {
//        model.addAttribute("products", productService.getAllProducts()); // Fetch products
//        return "user_dashboard"; // Render user_dashboard.html
//    }
    }
}
