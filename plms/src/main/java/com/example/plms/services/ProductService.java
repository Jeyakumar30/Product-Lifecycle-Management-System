package com.example.plms.services;

import com.example.plms.models.Product;
import com.example.plms.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    @Transactional // Ensure that the transaction is handled correctly
    public Product saveProduct(Product product) {
        if (product.getLifecycleStage() == null) {
            throw new IllegalArgumentException("Lifecycle Stage must be set");
        }
        return productRepository.save(product); // Persist the product
    }


    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> getProductsByLifecycleStage(String stageName) {
            return productRepository.findByLifecycleStage_Name(stageName);
    }

    public List<Product> getProductsByLifecycleStageId(Long stageId) {
            return productRepository.findByLifecycleStage_Id(stageId);
    }

    public List<Product> getProductsWithLifecycleStages() {
        return productRepository.findAllWithLifecycleStages();
    }

    public Map<String, Long> getLifecycleCounts() {
            List<Product> products = getAllProducts();
            return products.stream()
                    .collect(Collectors.groupingBy(product -> product.getLifecycleStage().getName(), Collectors.counting()));
        }
    }