package src;
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
    public void testSearch() throws IOException {
        assertEquals(expected, actual);
    }
}
