package com.crossover.hackhathon.repository;

import java.util.Optional;

import javax.persistence.QueryHint;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import com.crossover.hackhathon.model.RefUser;

@Repository
public interface RefUserRepository extends JpaRepository<RefUser, Long> {

	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
	RefUser findByUsername(String username);

	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
	Optional<RefUser> findOneByUsername(String username);

	Page<RefUser> findAll(Pageable pageable);
	
}
