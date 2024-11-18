package com.example.plms.controllers;

import com.example.plms.models.LifecycleStage;
import com.example.plms.services.LifecycleStageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/lifecycle-stages")
public class LifecycleStageController {
    private final LifecycleStageService lifecycleStageService;

    public LifecycleStageController(LifecycleStageService lifecycleStageService) {
        this.lifecycleStageService = lifecycleStageService;
    }

    @GetMapping
    public String listStages(Model model) {
        model.addAttribute("stages", lifecycleStageService.getAllStages());
        return "lifecycle_stages/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("lifecycleStage", new LifecycleStage());
        return "lifecycle_stages/create";
    }

    @PostMapping
    public String createStage(@ModelAttribute LifecycleStage lifecycleStage) {
        lifecycleStageService.saveStage(lifecycleStage);
        return "redirect:/lifecycle-stages";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        LifecycleStage stage = lifecycleStageService.getStageById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid stage ID: " + id));
        model.addAttribute("lifecycleStage", stage);
        return "lifecycle_stages/edit";
    }

    @PostMapping("/{id}")
    public String updateStage(@PathVariable Long id, @ModelAttribute LifecycleStage lifecycleStage) {
        lifecycleStage.setId(id);
        lifecycleStageService.saveStage(lifecycleStage);
        return "redirect:/lifecycle-stages";
    }

    @PostMapping("/{id}/delete")
    public String deleteStage(@PathVariable Long id) {
        lifecycleStageService.deleteStage(id);
        return "redirect:/lifecycle-stages";
    }
}