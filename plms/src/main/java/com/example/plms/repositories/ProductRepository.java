package com.example.plms.repositories;

import com.example.plms.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByLifecycleStage_Name(String stageName);

    List<Product> findByLifecycleStage_Id(Long stageId);

    @Query("SELECT p FROM Product p WHERE p.lifecycleStage.name = :stageName")
    List<Product> findByStageName(@Param("stageName") String stageName);

    @Query("SELECT COUNT(p) FROM Product p WHERE p.lifecycleStage.name = :lifecycleStageName")
    long countByLifecycleStageName(String lifecycleStageName); // Use lifecycleStage.name for comparison

    // Query to fetch all distinct lifecycle stages from the database
    @Query("SELECT DISTINCT p.lifecycleStage.name FROM Product p")
    List<String> findDistinctStages();
}


