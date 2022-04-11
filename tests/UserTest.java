/**
 * author: Maeko Maja
 */
package tests;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.io.IOException;
import org.json.simple.parser.ParseException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import src.User;
import src.GuestUser;
import src.RegisteredUser;
import src.BusinessUser;
import src.FlightBooking;
import src.HotelBooking;
import src.UserManager;
import src.Utilities;
import java.util.*;


class UserTest {
    private User user;
    private RegisteredUser reguser;
    private GuestUser guest;
    private BusinessUser business;
    private UserManager app;
    private Utilities util;


    @BeforeEach
    public void setup() throws IOException, ParseException {
       reguser = new RegisteredUser("ooga", "booga", null, null,new ArrayList<FlightBooking>(),new ArrayList<HotelBooking>(), new ArrayList<GuestUser>());
       util = util.getInstance();
       business = new BusinessUser("weewaw", "yeehaw", null, null);

    }

    @AfterEach
    public void tearDown() {

    }

    @Test
    void testNullValidUser() {
        reguser = new RegisteredUser(null, null, null, null);
        assertEquals("Doesn't exist", reguser.getUsername());
    }

    @Test 
    void testRegWrongPassword() {
        assertEquals(false, reguser.checkPassword("brooga"));
    }

    @Test 
    void testRightPassword() {
        assertEquals(true, reguser.checkPassword("booga"));
    }

    @Test
    void testGuestEquals() {
        guest = new GuestUser("ooga");
        assertEquals(true, guest.equals(guest));
    }

    @Test
    void testNullBusinessUser() {
        business = new BusinessUser(null, null, null, null);
        assertEquals("Doesn't exist", business.getUsername());
    }

    @Test
    void testPasswordBusinessUser() {
        business = new BusinessUser(null, "", null, null);
        assertEquals("Invalid", business.getPassword());
    }

    
    
}
