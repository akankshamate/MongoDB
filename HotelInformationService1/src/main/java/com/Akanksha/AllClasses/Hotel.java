package com.Akanksha.AllClasses;

import java.util.ArrayList;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Hotels") //It is used for creating collection in mongodb.
public class Hotel {
	@Id
	private String id;
	private String name;
	
	@Indexed(direction=IndexDirection.ASCENDING)
	private int pricePerDay;
	private Address address;
	private List<Review> reviews;
	
	protected Hotel() {
		this.reviews=new ArrayList<>();
	}

	/**
	 * @param id
	 * @param name
	 * @param pricePerDay
	 * @param address
	 * @param reviews
	 */
	public Hotel(String name, int pricePerDay, Address address, List<Review> reviews) {
		super();
		this.name = name;
		this.pricePerDay = pricePerDay;
		this.address = address;
		this.reviews = reviews;
	}
	
	/**
	 * 
	 * @return id of the particular field
	 */
	public String getId() {
		return id;
	}

	/**
	 * 
	 * @return name of the hotel
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 
	 * @return per day price of the hotel
	 */
	public int getPricePerDay() {
		return pricePerDay;
	}

	/**
	 * @see Address class which has country and city field   
	 * @return address of the hotel
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * @see Review class which has username,rating and approved field
	 * @return list of reviws
	 */
	public List<Review> getReviews() {
		return reviews;
	}

	

}
