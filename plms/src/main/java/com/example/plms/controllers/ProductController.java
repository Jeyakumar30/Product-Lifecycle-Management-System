package com.example.plms.controllers;

import com.example.plms.models.LifecycleStage;
import com.example.plms.models.Product;
import com.example.plms.services.LifecycleStageService;
import com.example.plms.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    private LifecycleStageService lifecycleStageService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String listProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "products/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("stages", lifecycleStageService.getAllStages()); // Add stages to the model
        return "products/create";
    }
    @PostMapping("/create")
    public String createProduct(@ModelAttribute Product product) {
        System.out.println("Product Lifecycle Stage: " + product.getLifecycleStage());
        productService.saveProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/{id}")
    public String viewProduct(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product ID: " + id));
        model.addAttribute("product", product);
        return "products/view";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id).orElseThrow(() -> new IllegalArgumentException("Invalid product ID: " + id));
        List<LifecycleStage> lifecycleStages = lifecycleStageService.getAllLifecycleStages();  // Fetch all available lifecycle stages
        model.addAttribute("product", product);
        model.addAttribute("lifecycleStages", lifecycleStages);  // Pass lifecycle stages to the view
        return "products/edit";
    }

    @PostMapping("/{id}/edit")
    public String updateProduct(@PathVariable Long id, @ModelAttribute Product product) {
        product.setId(id);
        productService.saveProduct(product);
        return "redirect:/products";
    }

    @PostMapping("/{id}/delete")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }

    @GetMapping("/stage/{stageName}")
    public String getProductsByStage(@PathVariable String stageName, Model model) {
        List<Product> products = productService.getProductsByLifecycleStage(stageName);
        model.addAttribute("products", products);
        return "products/list";
    }

    @GetMapping("/view")
        public String viewPublicProducts(Model model) {
            // Fetch all products with their lifecycle stages
            List<Product> products = productService.getAllProducts(); // Ensure this fetches the required data
            model.addAttribute("products", products);
            return "products/view"; // Points to templates/products/view.html
        }
    }