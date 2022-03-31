package src;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

//import javax.lang.model.util.ElementScanner14;

import org.json.simple.parser.ParseException;

/**
 * Application Manager class manages the whole application
 */

public class ApplicationManager {

    private static ApplicationManager appManager;
    private static UserManager userManager;
    private static BookingManager bookingManager;


    /**
     * private constructor
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ParseException
     * @throws java.text.ParseException
     */
    private ApplicationManager() throws FileNotFoundException, IOException, ParseException, java.text.ParseException{
        Utilities.getInstance();
        userManager = UserManager.getInstance();
        bookingManager = BookingManager.getInstance();
    }

    /**
     * gets instance of Application Manager class
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ParseException
     * @throws java.text.ParseException
     */
    public static ApplicationManager getInstance() throws FileNotFoundException, IOException, ParseException, java.text.ParseException {

        if (appManager == null)
            appManager = new ApplicationManager();

        return appManager;
        
    }

    /**
     * signs up a new User
     * @param username
     * @param password
     * @param dob
     * @param type
     * @return
     * @throws IOException
     */
    public boolean signUp(String username, String password, Date dob, String type) throws IOException{

        User user;

        Date creationDate = new Date();

        if (type.equalsIgnoreCase("Registered"))
            user = new RegisteredUser(username, password, dob, creationDate);
        else if (type.equalsIgnoreCase("Business"))
            user = new BusinessUser(username, password, dob, creationDate);
        else
            return false;


        return userManager.addUser(username,user);
        
    }

    /**
     * logs out current user
     * @throws IOException
     */
    public void logout() throws IOException {
        userManager.logout();
    }

    /**
     * closes application
     * @throws IOException
     */
    public void close() throws IOException{
        Utilities.saveHotels(bookingManager.getHotels()); //Uncomment this when to json object is complete
        Utilities.savePlanes(bookingManager.getPlanes());
        Utilities.saveUsers(userManager.getUsers());
    }


    /**
     * searches for flights based on parameters
     * @param departure city to be departed from
     * @param destination place to be transported to
     */
    public void searchFlights(String departure, String destination){
        bookingManager.searchFlights(departure, destination);
    }

    /**
     * searches for hotels based on parameters
     * @param search destination for which the hotel will be
     */
    public void searchHotels(String search){
        bookingManager.searchHotels(search);   
    }

    /**
     * books flights
     * @param choice number of booking
     * @return boolean value to see if the choice exists
     */
    public boolean bookFlight(int choice) {
        try {
            return bookingManager.bookFlight(choice);
        } catch (ParseException | java.text.ParseException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    /**
     * books hotels
     * @param choice number of booking
     * @return boolean value to see if the choice exists
     */
    public boolean bookHotel(int choice) {
        try {
            return bookingManager.bookHotel(choice);
        } catch (ParseException | java.text.ParseException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    /**
     * logs existing user in
     * @param username user name
     * @param password password
     * @return boolean value on whether or not the user exists
     */
    public boolean login(String username, String password) {
        return (userManager.login(username, password));
    }

    /**
     * returns current user
     * @return the User currently on the application
     */
    public User getCurrentUser() {
        return userManager.getCurrentUser();
    }    
}
