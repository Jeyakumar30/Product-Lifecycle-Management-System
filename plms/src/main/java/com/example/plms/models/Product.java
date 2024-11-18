package com.example.plms.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @ManyToOne
    @JoinColumn(name = "lifecycle_stage_id", nullable = false)
    private LifecycleStage lifecycleStage; // Foreign key to LifecycleStage

    public String getLifecycleStageName() {
        return lifecycleStage != null ? lifecycleStage.getName() : "Unknown";
    }
}
