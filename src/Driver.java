package src;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;
import java.util.Scanner;

public class Driver {

    private static Users currentUser;
    private static ApplicationManager appManager;

    private static boolean run(){

        Scanner input=new Scanner(System.in);
        //cheapest flights
        mainMenu();
        int action=input.nextInt();

        if(action==1) {
            //new user
        }
        else if(action==2) {
            //login
        }
        else if(action==3) {
            //search flights
        }
        else if(action==4) {
            //search hotels
        }
        else if(action==5) {
            //review
        }
        else if(action==6) {
            //logout
        }

        return false;
    }
    
    public static void main(String args[]) throws FileNotFoundException, IOException, ParseException, java.text.ParseException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {

        long start = System.nanoTime();
        appManager = ApplicationManager.getInstance();

        Utilities.generateFlights();

        HashMap<String, Users> user = new HashMap();

        user.put("tomjon", new RegisteredUser("tomjon", "jj", new Date(), new Date(), new ArrayList<FlightBooking>(), new ArrayList<HotelBooking>(), new String[]{}));
        user.put("tomjon2", new BusinessUser("tomjon2", "jj", new Date(), new Date(), new Airlines[]{Airlines.DELTA}, new Hotels[]{Hotels.AVARI}));

        //Utilities.saveUsers(user);
        while(run());
        long end = System.nanoTime();

        System.out.println("Execution took " + (end-start)/1000000 + " ms");

        
            
    }

    public void mainMenu() {
        System.out.println("Hello, "+currentUser.getUsername()+"!\n");
        System.out.println("1. Sign Up\n2. Log In\n3. Search Flights\n4. Search Hotels\n5.Review a Hotel/Airlines
        \n6. Log Out\nWhat would you like to do?\n");
    }

    public void userMenu() {
        System.out.println("Welcome, "+currentUser.getUsername()+"!\n");
        System.out.println("1. Set/Change Preferences\n2. Change Password\n3. View Past Bookings\n
        4. View Current Bookings\n5. Return to Main Menu\nWhat would you like to do?\n");
    }

    public void busUserMenu() {
        System.out.println("Welcome, "+currentUser.getUsername()+"!\n");
        System.out.println("1. Add Booking\n2. Delete Booking\n3. Change Booking\n4. View Current
         Bookings\n5. Return to Main Menu\nWhat would you like to do?\n");
    }


}