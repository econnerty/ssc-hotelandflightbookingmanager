/**
 * author: Maeko Maja
 */
package tests;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import src.Hotel;
import src.Hotels;

class HotelTest {
    private Hotel hotel;

    @BeforeEach
    public void setup() {
        hotel = new Hotel(null, 300, Hotels.MARRIOTT, 45.67, "Brooklyn", true, true, 3.4);
    }

    @AfterEach
    public void tearDown() {

    }

    @Test
    void testValidHotels() {
        hotel = new Hotel(null, 0, null, 0, null, true, true, 0);
        assertEquals("Doesn't exist", hotel.getHotelInfo());
    }

    @Test 
    void testHotelPrice() {
        hotel = new Hotel(null, 0, null, -45, null, true, true, 0);
        assertEquals("Invalid Price", hotel.getPrice());
    }

    @Test 
    void testHotelCity() {
        hotel = new Hotel(null, 0, null, 0, null, true, true, 0);
        assertEquals("Invalid City", hotel.getCity());
    }
}