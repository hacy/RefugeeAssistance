package com.crossover.hackhathon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crossover.hackhathon.model.address.Address;


@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
