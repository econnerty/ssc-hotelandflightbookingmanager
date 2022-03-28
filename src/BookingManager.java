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


    private BookingManager() throws FileNotFoundException, IOException, ParseException, java.text.ParseException{

        planes = Utilities.loadPlanes(); //This should allow us to get the full list of planes from the JSON
        hotels = Utilities.loadHotels(); //This should allow us to get the full list of hotels from the JSON

    }

    public static BookingManager getInstance() throws FileNotFoundException, IOException, ParseException, java.text.ParseException {

        if (bookingManager == null)
            bookingManager = new BookingManager();

        return bookingManager;   

    }

    public ArrayList<Plane> getFlights(String search, int severity) {
        ArrayList<Plane> results = new ArrayList<Plane>();
        String[] searchWords = search.split(" ");
        for(int i=0;i<planes.size();i++) {//iterate through each archive in planes.
            for(int j=0;j<searchWords.length;j++) {//iterate through each word in the user's search.
            //TODO check UUID
            //TODO check airline
            //TODO check availableSeats
            //TODO check price (if single number, then check for exactly that one. If 2 numbers sepparated by a dash then search for middling numbers)
            //TODO check departureDate
            //TODO check arrivalDate
            //TODO check destinationCity
            //TODO check departureCity
            //TODO check seats (available seats)
            //TODO check smoking ("smoking" vs "no-smoking")
            //TODO check petsAllowed ("pets" vs "no-pets")
            }
        }
        if(results.size() <=1 && severity >=0) {//add similar date keyword to search and run again and place extra results at the bottom.
            results = getFlights(search, severity--);
        }
        return results;
    }

    public ArrayList<Hotel> getHotels(String search, int severity) {
        ArrayList<Hotel> results = new ArrayList<Hotel>();
        String[] searchWords = search.split(" ");

        for(int i=0;i<hotels.size();i++) {//iterate through each entry in hotels.
            for(int j=0;j<searchWords.length;j++) {//iterate through each word in the user's search.

            }
        }
        if(results.size() <=1 && severity >=0) {//add broader location keyword to search and run again and place extra results at the bottom.

            results = getHotels(search, severity--);
        }
        return results;
    }
}
