package com.crossover.hackhathon.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.crossover.hackhathon.model.VocationalTraining;

public interface VocationalTrainingRepository extends JpaRepository<VocationalTraining, Long> {

	Page<VocationalTraining> findAll(Pageable pageable);

}
