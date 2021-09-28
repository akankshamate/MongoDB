package com.Akanksha.AllClasses;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends MongoRepository<Hotel,String>,
														QueryDslPredicateExecutor<Hotel> {
	
	Optional<Hotel> findById(String id);
	List<Hotel> findByPricePerDayLessThan(int max);
	
	@Query(value= "{address.city:?0}")
	List<Hotel> findByCity(String city);
}
