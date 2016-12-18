package com.crossover.hackhathon.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crossover.hackhathon.model.School;

@Repository
public interface SchoolRepository  extends JpaRepository<School, Long> {

	Page<School> findAll(Pageable pageable);

}
