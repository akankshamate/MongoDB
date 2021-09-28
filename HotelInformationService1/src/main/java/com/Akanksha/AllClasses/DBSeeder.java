package com.Akanksha.AllClasses;

import java.util.Arrays;


import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBSeeder implements CommandLineRunner {
	
	//Creating object of HotelRepository
	private HotelRepository hotelRepository;
	
	/**
	 * @param hotelRepository
	 */
	public DBSeeder(HotelRepository hotelRepository) {
		this.hotelRepository = hotelRepository;
	}

	@Override
	public void run(String... strings) throws Exception{
		
		
		Hotel taj=new Hotel("Taj Palace",5000,
				new Address("Mumbai","India"),
				Arrays.asList(new Review("Kajal",4,false),
				new Review("Anushka",5,true)
				)
		);
		Hotel JWM=new Hotel("JW Marriott",3000,
				new Address("Pune","India"),
				Arrays.asList(new Review("Akanksha",4,true))
		);
		
		//drop existing hotels
		this.hotelRepository.deleteAll();
		
		//add hotels to the database
		List<Hotel> hotels=Arrays.asList(taj,JWM);
		this.hotelRepository.save(hotels);
		
	}
}
