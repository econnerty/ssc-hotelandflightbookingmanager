package src;

import java.util.Date;
import java.util.UUID;

public class BusinessUser extends Users {

    private Airlines[] airlines; //enum for airlines
    private Hotels[] hotels; //enum for hotels
 
    public BusinessUser(String username, String password, Date dob, Date creationDate, Airlines[] airlines, Hotels[] hotels) {
        super(username, password, dob, creationDate);
        this.airlines = airlines;
        this.hotels = hotels;
    }

    
    public String toString() {
        return this.username + " " + this.password + " " + this.creationDate.toString() + " " + airlines.toString() + " "  + hotels.toString();
    }

    public Airlines[] getAirlines() {
        return airlines;
    }


    public Hotels[] getHotels() {
        return hotels;
    }


    public void setAirlines(Airlines[] airlines){
        this.airlines = airlines;
    }

    public void setHotels(Hotels[] hotels){
        this.hotels = hotels;
    }
    
}
