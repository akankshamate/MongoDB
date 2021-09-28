package com.Akanksha.AllClasses;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.types.dsl.BooleanExpression;

@RestController
@RequestMapping("/hotels")
public class HotelController {
	
	@Autowired
	private HotelRepository hotelRepository;

	/**
	 * It returns all data from the repository.
	 * @return list of hotels
	 */
	@GetMapping("/all")
	public List<Hotel> getAll(){
		//Returns All data from the repository
		List<Hotel> hotels=hotelRepository.findAll();
		return hotels;
	}
	
	/**
	 * //Saving the information entered by user into the repository
	 * @param hotel
	 */
	@PostMapping
	public void update(@RequestBody Hotel hotel) {
		this.hotelRepository.save(hotel);
	}
	
	/**
	 * //changing values of the existing entry 
	 * @param hotel
	 */
	@PutMapping
	public void insert(@RequestBody Hotel hotel) {
		this.hotelRepository.insert(hotel);
	}
	
	/**
	 * delete a particular data through the id
	 * @param id
	 */
	@DeleteMapping("{id}")
	public void delete(@PathVariable String id) {
		
		this.hotelRepository.delete(id);
	}
	
	/**
	 * {@code getById} getting the data from the repository by entering particular id
	 * @param id
	 * @return Optional<Hotel> class object
	 */
	@GetMapping("{id}")
	public Optional<Hotel> getById(@PathVariable("id") String id) {
		
		Optional<Hotel> hotel=this.hotelRepository.findById(id);
		return hotel;
	}
	
	/**
	 * {@code getPricePerDay} by using repositry's method if per day price is less than maxPrice
	 * then return that hotels data.
	 * @param maxPrice
	 * @return list of hotels
	 */
	@GetMapping("/price/{maxPrice}")
	public List<Hotel> getPricePerDay(@PathVariable("maxPrice") int maxPrice){
		List<Hotel> hotels=this.hotelRepository.findByPricePerDayLessThan(maxPrice);
		return hotels;
	}
	
	/**
	 * {@code getByCity} uses the method of repository and returning the list hotels which 
	 * have that city name.
	 * @param city
	 * @return list of hotels
	 */
	@GetMapping("/address/{city}")
	public List<Hotel> getByCity(@PathVariable("city") String city){
		List<Hotel> hotels=this.hotelRepository.findByCity(city);
		return hotels;
	}
	
	/**
	 * {@code getByCountry} if the entered country is equal to the any of the hotel's country
	 * then it returns that hotel list.
	 * @param country
	 * @return list of hotels
	 */
	@GetMapping("/country/{country}")
	public List<Hotel> getByCountry(@PathVariable("country") String country){
		
		//Create a query class
		QHotel qhotel=new QHotel("hotel");
		
		//Using the query class we can create filters
		BooleanExpression filterByCountry=qhotel.address.country.eq(country);
		
		//We can then pass the filter to the repositorie's findAll() method
		List<Hotel> hotels = (List<Hotel>) this.hotelRepository.findAll(filterByCountry);
		return hotels;
	}
	/**
	 * {@code getRecommended} have Query class QHotel and by comparing the values
	 *  of maxPrice and minRating it returns that list of hotels.
	 * @return list of hotels
	 */
	@GetMapping("/recommended")
	public List<Hotel> geteRecommended(){
		final int maxPrice=4000;
		final int minRating=2;
		
		QHotel qhotel=new QHotel("Hotel");
		
		//It returns true or false 
		BooleanExpression filterByPrice=qhotel.pricePerDay.lt(maxPrice);
		BooleanExpression filterByRating=qhotel.reviews.any().rating.gt(minRating);
		
		//If both the conditions are true then only it returns the list of hotels
		List<Hotel> hotels =(List<Hotel>) this.hotelRepository.findAll(filterByPrice.and(filterByRating));
		return hotels;
		
	}
}
