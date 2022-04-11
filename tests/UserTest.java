/**
 * author: Maeko Maja
 */
package tests;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import src.User;
import src.GuestUser;
import src.RegisteredUser;
import src.BusinessUser;
import src.FlightBooking;
import src.HotelBooking;


class UserTest {
    private User user;
    private RegisteredUser reguser;
    private GuestUser guest;
    private BusinessUser business;


    @BeforeEach
    public void setup() {
       reguser = new RegisteredUser("ooga", "booga", null, null,new ArrayList<FlightBooking>(),new ArrayList<HotelBooking>(), new ArrayList<GuestUser>());
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

    @Test
    void testAddFriends() {
        reguser.addFriends("Grace");
        assertEquals("Grace", reguser.getFriends());
    }

    @Test
    void testNullAddFriends() {
        reguser.addFriends(null);
        assertEquals(null, reguser.getFriends());
    }

    @Test
    void testNullRemoveFriends() {
        reguser.removeFriend("Grace");
        assertEquals(null, reguser.getFriends());
    }

    
    
}
