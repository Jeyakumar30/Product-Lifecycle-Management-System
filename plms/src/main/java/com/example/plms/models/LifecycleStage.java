package com.example.plms.models;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "lifecycle_stages")
public class LifecycleStage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    @OneToMany(mappedBy = "lifecycleStage", cascade = CascadeType.ALL)
    private List<Product> products; // Linked products
}