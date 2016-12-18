package com.crossover.hackhathon.repository;

import java.util.List;

import javax.persistence.QueryHint;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.crossover.hackhathon.RefugeeAssistConstants;
import com.crossover.hackhathon.model.address.TurkeyCityDistrict;

@Repository
public interface TurkeyCityDistrictRepository extends JpaRepository<TurkeyCityDistrict, Long> {

	@Cacheable(cacheNames = RefugeeAssistConstants.CITY_DISTRICT_CACHE)
	public TurkeyCityDistrict findOne(Long id);
	
	@SuppressWarnings("unchecked")
	@CachePut(cacheNames = RefugeeAssistConstants.CITY_DISTRICT_CACHE)
	public TurkeyCityDistrict save(TurkeyCityDistrict turkeyCityDistrict);	

	@CacheEvict(cacheNames = RefugeeAssistConstants.CITY_DISTRICT_CACHE)
	public void delete(Long id);	
	
	@Cacheable(cacheNames = RefugeeAssistConstants.CITY_DISTRICT_CACHE, key = "#root.methodName")
    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    @Query("select district from TurkeyCityDistrict district order by district.name")
	List<TurkeyCityDistrict> findAllOrderByName();
    
	@Cacheable(cacheNames = RefugeeAssistConstants.CITY_DISTRICT_CACHE, key = "#root.methodName + #root.args[0]")
    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    @Query("select district from TurkeyCityDistrict district where district.turkeyCity.id = :turkeyCityId order by district.name")
	List<TurkeyCityDistrict> findAllByTurkeyCityIdOrderByName(@Param("turkeyCityId") Long turkeyCityId);
	
}
