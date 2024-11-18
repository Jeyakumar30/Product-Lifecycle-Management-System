package com.example.plms.repositories;

import com.example.plms.models.LifecycleStage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LifecycleStageRepository extends JpaRepository<LifecycleStage, Long> {
}