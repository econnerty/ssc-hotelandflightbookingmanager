package src;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import org.json.simple.JSONArray;

import java.util.ArrayList;


public class RegisteredUser extends User {

    private ArrayList<FlightBooking> flightBookings; //Bookings can just be a tuple array in users. The string will be the uuid to the plane or hotel. The int array will hold the coordinates to the room or the plane's seat
    private ArrayList<HotelBooking> hotelBookings; //Bookings can just be a tuple array in users. The string will be the uuid to the plane or hotel. The int array will hold the coordinates to the room or the plane's seat
    private String[] preferences;

    public RegisteredUser(String username, String password, Date dob, Date creationDate, ArrayList<FlightBooking> flightBookings, ArrayList<HotelBooking> hotelBookings, String[] preferences) {
        super(username, password, dob, creationDate);
        this.flightBookings = flightBookings;
        this.hotelBookings = hotelBookings;
        this.preferences = preferences;
    }

    public ArrayList<FlightBooking> getFlightBookings() {
        return this.flightBookings;
    }

    public ArrayList<HotelBooking> getHotelBookings() {
        return this.hotelBookings;
    }

    public void setPreferences() {
        //TODO
    }

    public void addBooking() {
        //TODO
    }

    public void cancelBooking() {
        //TODO
    }

    public String menuString() {

        return "Welcome, "+this.username+"!\n1. Set/Change Preferences\n2. Change Password\n3. View Past Bookings\n4. View Current Bookings\n5. Return to Main Menu\n\nWhat would you like to do?\n";
        
    }

    
    public Map toJsonObject() {

        Map jsonObject = new LinkedHashMap<>();

        jsonObject.put("type","registered");
        jsonObject.put("username",this.username);
        jsonObject.put("creationDate", this.creationDate);
        jsonObject.put("password", this.password);
        
        String dob = dobFormat.format(this.dob);
        //System.out.println(dob);
        jsonObject.put("dob", dob);

        JSONArray jsonFlight = new JSONArray();
        for (FlightBooking booking : this.flightBookings) {
            jsonFlight.add(booking.getUUID().toString());
            jsonFlight.add(String.valueOf(booking.getIndex()[0]));
            jsonFlight.add(String.valueOf(booking.getIndex()[1]));
        }
        jsonObject.put("flightBookings", jsonFlight);

        JSONArray jsonHotel = new JSONArray();
        for (HotelBooking booking : this.hotelBookings) {
            jsonHotel.add(booking.getUUID().toString());
            jsonHotel.add(String.valueOf(booking.getIndex()[0]));
            jsonHotel.add(String.valueOf(booking.getIndex()[1]));
            jsonHotel.add(String.valueOf(booking.getIndex()[2]));
        }
        jsonObject.put("hotelBookings", jsonHotel);
    
        return jsonObject;
            
    }

    
    public String toString() {
        String ret = this.username + " " + this.password + " " + this.creationDate.toString() + " ";

        for (FlightBooking f : flightBookings)
            ret += f.toString() +" ";
        
        for (HotelBooking f : hotelBookings)
            ret += f.toString() + " ";

        return ret;
    }

}