package src;

import java.util.Date;
import java.util.UUID;

public class BusinessUser extends Users {

    private Airlines[] airlines; //enum for airlines
    private Hotels[] hotels; //enum for hotels
 
    public BusinessUser(String name, String password, Date dob, Date creationDate, Airlines[] airlines, Hotels[] hotels) {
        super(name, password, dob, creationDate);
        this.airlines = airlines;
        this.hotels = hotels;
    }

    
    public String toString() {
        return this.name + " " + this.password + " " + this.creationDate.toString() + " " + airlines.toString() + " "  + hotels.toString();
    }

    public void setAirlines(Airlines[] airlines){
        this.airlines = airlines;
    }

    public void setHotels(Hotels[] hotels){
        this.hotels = hotels;
    }
    
}
