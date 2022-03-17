package src;

import java.util.ArrayList;
import java.util.HashMap;

public class BookingManager {

    private static BookingManager bookingManager;

    private static HashMap<String,Plane> planes; //We will store all the planes in this hashmap. The uuid will be the key.
    private static HashMap<String,Hotel> hotels; //We will store all the hotels in this hashmap. The uuid will be the key.


    private BookingManager(){

        planes = Utilities.loadPlanes(); //This should allow us to get the full list of planes from the JSON
        hotels = Utilities.loadHotels(); //This should allow us to get the full list pf hotels from the JSON

    }

    public static BookingManager getInstance() {

        if (bookingManager == null)
            bookingManager = new BookingManager();

        return bookingManager;   

    }
    
}
