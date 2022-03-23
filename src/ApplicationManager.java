package src;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

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

    public boolean signUp(){
        return userManager.addUser();
        
    }


    public ArrayList<Plane> searchFlights(String search){
        return bookingManager.getFlights(search);
        
    }

    public ArrayList<Hotel> searchHotels(String search){
        return bookingManager.getHotels(search);
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
