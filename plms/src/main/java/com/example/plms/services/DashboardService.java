package com.example.plms.services;

import com.example.plms.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DashboardService {

    @Autowired
    private ProductRepository productRepository;

    public Map<String, Long> getProductCountByStage() {
        Map<String, Long> productCountByStage = new HashMap<>();

        // Fetch all distinct stages from the database
        List<String> distinctStages = productRepository.findDistinctStages();

        // For each distinct stage, count the number of products
        for (String stage : distinctStages) {
            long count = productRepository.countByLifecycleStageName(stage); // Use the updated method
            productCountByStage.put(stage, count);
        }

        return productCountByStage;
    }

}