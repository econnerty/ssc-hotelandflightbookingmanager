package src;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import org.json.simple.JSONArray;

public class BusinessUser extends User {

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

    public String menuString() {
        
        return "Welcome, "+ this.username+"!\n1. Add Booking\n2. Delete Booking\n3. Change Booking\n4. View Current Bookings\n5. Return to Main Menu\n\nWhat would you like to do?\n";
    }

    public Map toJsonObject() {

        Map jsonObject = new LinkedHashMap<>();

        jsonObject.put("type","business");
        jsonObject.put("username",this.username);
        jsonObject.put("creationDate", this.getCreationDate().toString());
        jsonObject.put("password", this.password);
        String dob = dobFormat.format(this.dob);
        jsonObject.put("dob", dob);


        JSONArray jsonAirlines = new JSONArray();
        JSONArray jsonHotels = new JSONArray();

        for (Airlines airline : this.airlines) {
            jsonAirlines.add(airline.toString());
        }

        
        for (Hotels hotel : this.hotels) {
            jsonHotels.add(hotel.toString());
        }

        jsonObject.put("airlines", jsonAirlines);
        jsonObject.put("hotels", jsonHotels);

        return jsonObject;
    }
}
