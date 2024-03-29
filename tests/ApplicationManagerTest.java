/**
 * @author Shaine Moore
 */
package tests;
import static org.junit.jupiter.api.Assertions.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import javax.swing.text.FieldView;
import org.json.simple.parser.ParseException;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import src.ApplicationManager;
import src.BookingManager;
import src.UserManager;
import src.Utilities;
public class ApplicationManagerTest {
    private static ApplicationManager appManager;
    private static UserManager userManager;
    private static BookingManager bookingManager;
    @BeforeEach
    public void oneTimeSetup() throws FileNotFoundException, IOException, ParseException, java.text.ParseException {
        Utilities.getInstance();
        appManager = ApplicationManager.getInstance();
        userManager = UserManager.getInstance();
        bookingManager = BookingManager.getInstance();
    }
    @Test
    public void testSignUp() throws IOException { //Testing whether Signup Works.
        assertTrue(appManager.signUp("five", "five", new Date(), "registered"));
        userManager.removeUser("five");
    }
    @Test
    public void testDuplicatedSignup() throws IOException { //Testing that a user cannot have a taken username.
        assertFalse(appManager.signUp("tom", "tom", new Date(), "registered"));
    }
    @Test
    public void testLogin() {
        assertTrue(appManager.login("tom", "1234"));
    }
    @Test
    public void testInvalidLogin() {
        assertFalse(appManager.login("jim","jim"));
        assertFalse(appManager.login("tom","12"));
    }
    @Test
    public void testLogout() throws IOException {
        assertTrue(appManager.login("tom","1234"));
        appManager.logout();
        assertEquals(appManager.getCurrentUser().getUsername(), "Guest");
    }
    @Test
    public void testSearchFlights(){
        appManager.searchFlights("columbia", "seattle");
        assertTrue(bookingManager.getSearchResults().size() == 4); //There are 4 flights from columbia to seattle
    }
    @Test
    public void testSearchHotels(){
        appManager.searchHotels("seattle");
        assertTrue(bookingManager.getHotelSearchResults().size() == 4); //There are 4 hotels in our json for Seatle.
    }
    /*@Test
    public void testBookHotel() { //requres input from terminal, cannot automate.
        appManager.searchHotels("seattle");
    }
    @Test 
    public void testBookFlight() { //requres input from terminal, cannot automate.
        appManager.searchFlights("columbia", "seattle");
    }*/
}