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

    private static HashMap<UUID, Business> businesses;


    private BookingManager() throws FileNotFoundException, IOException, ParseException, java.text.ParseException{

        planes = Utilities.loadPlanes(); //This should allow us to get the full list of planes from the JSON
        hotels = Utilities.loadHotels(); //This should allow us to get the full list of hotels from the JSON
        businesses = Utilities.loadBusinesses();


    }

    public static BookingManager getInstance() throws FileNotFoundException, IOException, ParseException, java.text.ParseException {

        if (bookingManager == null)
            bookingManager = new BookingManager();

        return bookingManager;   

    }

    public void bookFlight(UUID uuid, int[] index){

    }

    public ArrayList<Plane> getFlights(String search) {

        search = search.toLowerCase();

        String query[] = search.split(" ");
        ArrayList<Plane> results = new ArrayList<Plane>();
        if(search == "" || search == " ")
            return results;
        for (Plane plane : planes.values()) {//iterate through each archive in planes.
            if (plane.getDestinationCity().toLowerCase().contains(search)|| search.equalsIgnoreCase(plane.getDestinationCity())){
                results.add(plane);
            }

        }
 
        return results;
    }

    public ArrayList<Hotel> getHotels(String search) {
        search = search.toLowerCase();
        ArrayList<Hotel> results = new ArrayList<Hotel>();
        if(search == "" || search == " ")
            return results;
        for (Hotel hotel : hotels.values()) {//iterate through each archive in planes.
            if (hotel.getCity().toLowerCase().contains(search)){
                results.add(hotel);
            }
        }
        return results;
    }
    private double doubleParser(String str) {
        double val;
        try {
            val = Double.parseDouble(str);
        }
        catch (NumberFormatException e) {
            val = -1;
        }
        return val;
    }
}
