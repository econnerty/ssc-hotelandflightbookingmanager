package src;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UtilitiesTest {
    private Utilities utils;
    private HashMap<UUID, Plane> planes;
    private HashMap<UUID, Hotel> hotels;
    private HashMap<String, User> users;

    @BeforeEach
    public void oneTimeSetup() {
        utils = Utilities.getInstance();
    }

    @AfterEach
    public void tearDown() {

    }

    @Test
    void testLoadPlanes() throws FileNotFoundException, IOException, ParseException, java.text.ParseException {
        planes = utils.loadPlanes();

        assertNotNull(planes);
        assertEquals(504, planes.size()); //There are 504 planes in our json file
    }

    @Test
    void testLoadUsers() throws FileNotFoundException, IOException, ParseException, java.text.ParseException {

        users = utils.loadUsers();

        assertNotNull(users);
        assertEquals("tom", users.get("tom").getUsername()); //Tom is a user in our json and should be in the hashmap

    }

    @Test
    void testLoadHotels() throws FileNotFoundException, IOException, ParseException, java.text.ParseException {

        hotels = utils.loadHotels();

        assertNotNull(hotels);
        assertEquals(4, hotels.size()); //There are 4 hotels in our json file

    }




    
}
