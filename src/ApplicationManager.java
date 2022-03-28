package src;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

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

    public void logout() {
        userManager.logout();
    }

    public void close(){
        
    }


    public /*ArrayList<Plane>*/void searchFlights(String search){
        System.out.println("Flight searches for: \""+search+"\"");
        bookingManager.getFlights(search, 2);
        //return bookingManager.getFlights(search, 3);
        
    }

    public /*ArrayList<Hotel>*/ void searchHotels(String search){
        System.out.println("Hotel searches for: \""+search+"\"");
        bookingManager.getHotels(search, 2);
        //return bookingManager.getHotels(search, 3);
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
