package tests;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.json.simple.parser.ParseException;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import src.BookingManager;
import src.Business;
import src.Hotel;
import src.Plane;
import src.Utilities;

class BookingManagerTest {
    
    private static BookingManager bookingManager;

    private static HashMap<UUID,Plane> planes; //We will store all the planes in this hashmap. The uuid will be the key.
    private static HashMap<UUID,Hotel> hotels; //We will store all the hotels in this hashmap. The uuid will be the key.

    private static HashMap<UUID, Business> businesses;

    private static ArrayList<Plane> searchResults;
    private static ArrayList<Hotel> searchResultsHotel;

    @BeforeEach
    public void setup() throws FileNotFoundException, IOException, ParseException, java.text.ParseException {
        Utilities.getInstance();
        bookingManager = BookingManager.getInstance();
        
        planes = Utilities.loadPlanes(); //This should allow us to get the full list of planes from the JSON
        hotels = Utilities.loadHotels(); //This should allow us to get the full list of hotels from the JSON
        businesses = Utilities.loadBusinesses();

    }

    @Test
    public void testSearchFlights(){
        bookingManager.searchFlights("columbia", "seattle");
        assertTrue(bookingManager.getSearchResults().size() == 4); //There are 4 flights from columbia to seattle

    }
    @Test
    public void testSearchHotels(){
        bookingManager.searchHotels("seattle");
        assertTrue(bookingManager.getHotelSearchResults().size() == 4); //There are 4 flights from columbia to seattle

    }

    @AfterEach
    public void tearDown() {

    }


    
}
