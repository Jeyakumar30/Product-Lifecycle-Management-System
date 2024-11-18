package com.example.plms.services;

import com.example.plms.models.LifecycleStage;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.plms.repositories.LifecycleStageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LifecycleStageService {
    @Autowired
    private final LifecycleStageRepository lifecycleStageRepository;

    public LifecycleStageService(LifecycleStageRepository lifecycleStageRepository) {
        this.lifecycleStageRepository = lifecycleStageRepository;
    }

    public List<LifecycleStage> getAllStages() {
        return lifecycleStageRepository.findAll();
    }

    public Optional<LifecycleStage> getStageById(Long id) {
        return lifecycleStageRepository.findById(id);
    }

    public LifecycleStage saveStage(LifecycleStage stage) {
        return lifecycleStageRepository.save(stage);
    }

    public void deleteStage(Long id) {
        lifecycleStageRepository.deleteById(id);
    }

    public List<LifecycleStage> getAllLifecycleStages() {
        return lifecycleStageRepository.findAll();  // Return all lifecycle stages
    }
}