package src;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.ArrayList;

import org.javatuples.*;

public class RegisteredUser extends Users {

    private ArrayList<FlightBooking> flightBookings; //Bookings can just be a tuple array in users. The string will be the uuid to the plane or hotel. The int array will hold the coordinates to the room or the plane's seat
    private ArrayList<HotelBooking> hotelBookings; //Bookings can just be a tuple array in users. The string will be the uuid to the plane or hotel. The int array will hold the coordinates to the room or the plane's seat
    private String[] preferences;

    public RegisteredUser(String name, String password, Date creationDate, ArrayList<FlightBooking> flightBookings, ArrayList<HotelBooking> hotelBookings, String[] preferences) {
        super(name, password, creationDate);
        this.flightBookings = flightBookings;
        this.hotelBookings = hotelBookings;
        this.preferences = preferences;
    }

    public String toString() {
        String ret = this.name + " " + this.password + " " + this.creationDate.toString() + " ";

        for (FlightBooking f : flightBookings)
            ret += f.toString() +" ";
        
        for (HotelBooking f : hotelBookings)
            ret += f.toString() + " ";

        return ret;
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

}