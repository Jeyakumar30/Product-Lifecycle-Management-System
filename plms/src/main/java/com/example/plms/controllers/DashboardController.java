package com.example.plms.controllers;

import com.example.plms.models.Product;
import com.example.plms.services.DashboardService;
import com.example.plms.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    private final ProductService productService;

    @Autowired
    public DashboardController(ProductService productService) {
        this.productService = productService;
    }


//    @GetMapping("/dashboard")
//    public String showDashboard(Model model) {
//        Map<String, Long> productCountByStage = dashboardService.getProductCountByStage();
//
//        model.addAttribute("productCountByStage", productCountByStage);
//
//        return "dashboard";
//    }

    @GetMapping("/dashboard")
    public String showUserDashboard(Model model) {
        List<Product> products = productService.getAllProducts();

        // Aggregate product counts by lifecycle stage
        Map<String, Long> lifecycleCounts = products.stream()
                .collect(Collectors.groupingBy(Product::getLifecycleStageName, Collectors.counting()));

        // Prepare data for the chart
        model.addAttribute("lifecycleCounts", lifecycleCounts);
        model.addAttribute("products", products); // To display the table as well
        return "user_dashboard";
    }
}
