package com.crossover.hackhathon.repository;

import java.util.List;

import javax.persistence.QueryHint;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import com.crossover.hackhathon.RefugeeAssistConstants;
import com.crossover.hackhathon.model.address.TurkeyCity;


@Repository
public interface TurkeyCityRepository extends JpaRepository<TurkeyCity, Long> {

	@Cacheable(cacheNames = RefugeeAssistConstants.CITY_CACHE)
	public TurkeyCity findOne(Long id);
	
	@SuppressWarnings("unchecked")
	@CachePut(cacheNames = RefugeeAssistConstants.CITY_CACHE)
	public TurkeyCity save(TurkeyCity turkeyCity);	

	@CacheEvict(cacheNames = RefugeeAssistConstants.CITY_CACHE)
	public void delete(Long id);	
	
	@Cacheable(cacheNames = RefugeeAssistConstants.CITY_CACHE, key = "#root.methodName")
	@Query("select city from TurkeyCity city order by city.name")
	@QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value = "true") })
	public List<TurkeyCity> findAllOrderByName();
	
}
