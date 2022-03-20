package src;

import java.util.Date;
import java.util.UUID;
import java.util.ArrayList;


public class RegisteredUser extends Users {

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

    
    public String toString() {
        String ret = this.username + " " + this.password + " " + this.creationDate.toString() + " ";

        for (FlightBooking f : flightBookings)
            ret += f.toString() +" ";
        
        for (HotelBooking f : hotelBookings)
            ret += f.toString() + " ";

        return ret;
    }

}