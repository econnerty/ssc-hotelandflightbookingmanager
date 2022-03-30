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

    private static ArrayList<Plane> searchResults;

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

    public void close(){
        
    }


    public void searchFlights(String search){
        System.out.println("Flight searches for: \""+search+"\"");
        searchResults = bookingManager.getFlights(search);
        int i = 1;
        for(Plane plane : searchResults){
            System.out.println(i + plane.getFlightInfo());
            i++;
        }
        
    }

    public void bookFlight(int choice) {
        System.out.println("Choose a seat in the format (A4, A5): ");

        searchResults.get(choice-1).printSeats();
        Scanner input = new Scanner(System.in);
        String seats[] = input.nextLine().split(",");

        RegisteredUser registeredUser = (RegisteredUser) userManager.getCurrentUser();

        for (String seat : seats){
            switch (seat.charAt(0)) {
                case 'A':
                    int[] index = {1, Integer.parseInt(seat.substring(1,1))};
                    registeredUser.addFlightBooking(new FlightBooking(searchResults.get(choice-1).getUUID(), index));
                    break;
                case 'B':
                    int[] index2 = {1, Integer.parseInt(seat.substring(1,1))};
                    registeredUser.addFlightBooking(new FlightBooking(searchResults.get(choice-1).getUUID(), index2));
                    break;
                case 'C':
                    int[] index3 = {1, Integer.parseInt(seat.substring(1,1))};
                    registeredUser.addFlightBooking(new FlightBooking(searchResults.get(choice-1).getUUID(), index3));
                    break;
                case 'D':
                    int[] index4 = {1, Integer.parseInt(seat.substring(1,1))};
                    registeredUser.addFlightBooking(new FlightBooking(searchResults.get(choice-1).getUUID(), index4));
                    break;
                default:
                    break;

            }
            try {
                userManager.updateUser(registeredUser);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void searchHotels(String search){
        int i = 1;
        System.out.println("Hotel searches for: \""+search+"\"");
        for(Hotel hotel : bookingManager.getHotels(search)){
            System.out.println(i + "\t"+ hotel.getHotelInfo());
            i++;
        }
        
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
