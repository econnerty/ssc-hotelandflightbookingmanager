package src;

import java.util.Date;
import java.util.UUID;

public class BusinessUser extends Users {

    private Airlines[] airlines; //enum for airlines
    private Hotels[] hotels; //enum for hotels
 
    public BusinessUser(String name, String password, Date creationDate, Airlines[] airlines, Hotels[] hotels) {
        super(name, password, creationDate);
        this.airlines = airlines;
        this.hotels = hotels;
    }

    public void setAirlines(Airlines[] airlines){
        this.airlines = airlines;
    }

    public void setHotels(Hotels[] hotels){
        this.hotels = hotels;
    }
    
}
