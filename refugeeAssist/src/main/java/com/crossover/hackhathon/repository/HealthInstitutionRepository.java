package com.crossover.hackhathon.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.crossover.hackhathon.model.HealthInstitution;

public interface HealthInstitutionRepository extends JpaRepository<HealthInstitution, Long> {

	Page<HealthInstitution> findAll(Pageable pageable);

}
