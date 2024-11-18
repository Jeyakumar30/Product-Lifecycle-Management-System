package com.example.plms.services;

import com.example.plms.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StageService {

    @Autowired
    private ProductRepository productRepository;

    public List<String> getAllStages() {
        return productRepository.findDistinctStages(); // Fetch distinct stages from the database
    }
}
