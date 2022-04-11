package tests;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import src.Utilities;
import src.UserManager;
import src.User;


class UserManagerTest {

	private static UserManager userManager;
    private static User currentUser;
	
	 private static HashMap<String, User> users; //We will store all the users inside this hashmap. The UUID will be the key.

	@BeforeEach
	public void setup() throws FileNotFoundException, IOException, ParseException, java.text.ParseException, org.json.simple.parser.ParseException {
		Utilities.getInstance();
		userManager = UserManager.getInstance();
		
		
	}
	
	@Test
	public void testLogin() {
		assertTrue(userManager.login("tom","1234" ));
		
	}
	
	@Test
	public void testChangePassword() throws IOException {
		userManager.changePassword("1234","4321");
		assertEquals("4321", userManager.getCurrentUser().getPassword());
		userManager.changePassword("4321", "1234");
		Utilities.saveUsers(userManager.getUsers());
	}
	 @Test
	    public void testLogout() throws IOException {
	        assertTrue(userManager.login("tom","1234"));
	        userManager.logout();
	        assertEquals(userManager.getCurrentUser().getUsername(), "Guest");
	    }
	 @Test
	 	public void testaddUser() throws IOException{
		 userManager.addUser("bob", currentUser);
		 assertEquals("bob", userManager.getUsers());
		 userManager.removeUser("bob");
	 }
	
}
