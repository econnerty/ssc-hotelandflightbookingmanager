package src;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import org.json.simple.JSONArray;
/**
 * BusinessUser class for Business Users - extends the User class
 * and implements for JSON files
 */

public class BusinessUser extends User implements src.JSON {

    private Airlines[] airlines; //enum for airlines
    private Hotels[] hotels; //enum for hotels

    /**
     * BusinessUser constructor
     * @param username
     * @param password
     * @param dob date of birth
     * @param creationDate date of creation of account
     */
    public BusinessUser(String username, String password, Date dob, Date creationDate) {
        super(username, password, dob, creationDate);
    }
 
    /**
     * BusinessUser Constructor with different parameters
     * @param username
     * @param password
     * @param dob date of birth
     * @param creationDate date of creation of account
     * @param airlines
     * @param hotels
     */
    public BusinessUser(String username, String password, Date dob, Date creationDate, Airlines[] airlines, Hotels[] hotels) {
        super(username, password, dob, creationDate);
        this.airlines = airlines;
        this.hotels = hotels;
    }

    /**
     * prints details of BusinessUser as string
     */
    public String toString() {
        return this.username + " " + this.password + " " + this.creationDate.toString() + " " + airlines.toString() + " "  + hotels.toString();
    }

    /**
     * returns Airlines of BusinessUser
     * @return Airlines[]
     */
    public Airlines[] getAirlines() {
        return airlines;
    }

    /**
     * returns Hotels of BusinessUser
     * @return Hotels[]
     */
    public Hotels[] getHotels() {
        return hotels;
    }

    /**
     * sets current airlines to paramatered ones
     * @param airlines
     */
    public void setAirlines(Airlines[] airlines){
        this.airlines = airlines;
    }

    /**
     * sets current hotels to parametered ones
     * @param hotels
     */
    public void setHotels(Hotels[] hotels){
        this.hotels = hotels;
    }

    /**
     * Shows main menu
     * @return String
     */
    public String menuString() {
        
        return "\nWelcome, "+ this.username+"!\n1. Add Booking\n2. Delete Booking\n3. Change Booking\n4. View Current Bookings\n5. Return to Main Menu\n\nWhat would you like to do?";
    }

    /**
     * Setting up BusinessUsers into a JSON
     * @return Map
     */
    public Map toJsonObject() {

        Map jsonObject = new LinkedHashMap<>();

        jsonObject.put("type","business");
        jsonObject.put("username",this.username);
        jsonObject.put("creationDate", this.getCreationDate().toString());
        jsonObject.put("password", this.password);

        //String dobString = Utilities.dobFormat.format(this.dob);
        jsonObject.put("dob", Utilities.dobFormat.format(this.dob));


        JSONArray jsonAirlines = new JSONArray();
        JSONArray jsonHotels = new JSONArray();

        if (this.airlines != null)
            for (Airlines airline : this.airlines) {
                jsonAirlines.add(airline.toString());
            }

        if (this.hotels != null)
            for (Hotels hotel : this.hotels) {
                jsonHotels.add(hotel.toString());
            }

        jsonObject.put("airlines", jsonAirlines);
        jsonObject.put("hotels", jsonHotels);

        return jsonObject;
    }
}