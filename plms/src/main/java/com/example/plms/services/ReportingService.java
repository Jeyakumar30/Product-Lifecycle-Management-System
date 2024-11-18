package com.example.plms.services;

import com.example.plms.models.Product;
import com.example.plms.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportingService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getProductsByStage(String stageName) {
        return productRepository.findByStageName(stageName);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
