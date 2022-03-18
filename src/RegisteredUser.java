package src;

import java.util.Date;
import java.util.UUID;

import org.javatuples.*;

public class RegisteredUser extends Users {

    private Pair<String, int[]> flightBookings; //Bookings can just be a tuple in users. The string will be the uuid to the plane or hotel. The int array will hold the coordinates to the room or the plane's seat
    private Pair<String, int[]> hotelBookings; //Bookings can just be a tuple in users. The string will be the uuid to the plane or hotel. The int array will hold the coordinates to the room or the plane's seat
    private String[] preferences;

    public RegisteredUser(UUID uuid, String name, String password, Date creationDate, Pair<String, int[]> flightBookings, Pair<String, int[]> hotelBookings, String[] preferences) {
        super(uuid, name, password, creationDate);
        this.flightBookings = flightBookings;
        this.hotelBookings = hotelBookings;
        this.preferences = preferences;
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