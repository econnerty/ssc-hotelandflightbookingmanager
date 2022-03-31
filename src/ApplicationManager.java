package src;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

//import javax.lang.model.util.ElementScanner14;

import org.json.simple.parser.ParseException;

public class ApplicationManager {

    private static ApplicationManager appManager;
    private static UserManager userManager;
    private static BookingManager bookingManager;



    private ApplicationManager() throws FileNotFoundException, IOException, ParseException, java.text.ParseException{
        Utilities.getInstance();
        userManager = UserManager.getInstance();
        bookingManager = BookingManager.getInstance();
    }

    public static ApplicationManager getInstance() throws FileNotFoundException, IOException, ParseException, java.text.ParseException {

        if (appManager == null)
            appManager = new ApplicationManager();

        return appManager;
        
    }

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

    public void logout() throws IOException {
        userManager.logout();
    }

    public void close() throws IOException{
        Utilities.saveHotels(bookingManager.getHotels()); //Uncomment this when to json object is complete
        Utilities.savePlanes(bookingManager.getPlanes());
        Utilities.saveUsers(userManager.getUsers());
    }


    public void searchFlights(String departure, String destination){
        bookingManager.searchFlights(departure, destination);
    }


    public void searchHotels(String search){
        bookingManager.searchHotels(search);   
    }

    public boolean bookFlight(int choice) {
        try {
            return bookingManager.bookFlight(choice);
        } catch (ParseException | java.text.ParseException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    public boolean bookHotel(int choice) {
        try {
            return bookingManager.bookHotel(choice);
        } catch (ParseException | java.text.ParseException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    public boolean login(String username, String password) {
        return (userManager.login(username, password));
    }

    public User getCurrentUser() {
        return userManager.getCurrentUser();
    }

    public boolean print() {
        //TODO
        return false;
    }
    
}
