package com.Akanksha.AllClasses;

public class Address {
	
	private String city;
    private String country;

    protected Address(){}
    
    /**
     * 
     * @param city
     * @param country
     */
    public Address(String city, String country) {
		this.city = city;
		this.country = country;
	}
    
    /**
     * 
     * @return name of the city
     */
    public String getCity() {
        return city;
    }

    /**
     * 
     * @return country name
     */
    public String getCountry() {
        return country;
    }
}
