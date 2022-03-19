package src;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.json.simple.parser.ParseException;

public class BookingManager {

    private static BookingManager bookingManager;

    private static HashMap<UUID,Plane> planes; //We will store all the planes in this hashmap. The uuid will be the key.
    private static HashMap<UUID,Hotel> hotels; //We will store all the hotels in this hashmap. The uuid will be the key.


    private BookingManager() throws FileNotFoundException, IOException, ParseException{

        planes = Utilities.loadPlanes(); //This should allow us to get the full list of planes from the JSON
        hotels = Utilities.loadHotels(); //This should allow us to get the full list pf hotels from the JSON

    }

    public static BookingManager getInstance() throws FileNotFoundException, IOException, ParseException {

        if (bookingManager == null)
            bookingManager = new BookingManager();

        return bookingManager;   

    }
    
}
