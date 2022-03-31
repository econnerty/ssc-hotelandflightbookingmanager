package src;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import java.util.Scanner;
/**
 * This contains the main method and UI for the program 
 */
public class Driver {

    private static User currentUser;
    private static ApplicationManager appManager;

    private static final String[] types = {"Business", "Registered"};

    private static final Scanner input = new Scanner(System.in);
    /**
     * This constructor runs the program 
     *  @param boolean this return true if the program is still running
     *  @throws FileNotFoundException
     *  @throws IOException
     *  @throws ParseException
     *  @throws java.text.ParseException
     */
    private static boolean run() throws java.text.ParseException, IOException, ParseException{

        currentUser = appManager.getCurrentUser();

        if (currentUser.getClass() == GuestUser.class)
            return mainMenu();
        else if (currentUser.getClass() == BusinessUser.class)
            return businessMainMenu();
        else if (currentUser.getClass() == RegisteredUser.class)
            return registeredMainMenu();

        return false;
    }
    /**
     * This is the main menu for the guest user 
     * @param boolean this return true if the main menu is still running
     * @throws IOException
     * @throws java.text.ParseException
     */
    public static boolean mainMenu() throws IOException, java.text.ParseException {
        //cheapest flights

        System.out.println(currentUser.menuString());

        int action = Integer.parseInt(input.nextLine());

        String username;
        String password;


        switch (action) {
            case 1:
                System.out.println("Please enter your desired username: ");
                username = input.nextLine();

                System.out.println("Please enter your desired password: ");
                password = input.nextLine();

                System.out.println("Please enter your DOB in the format MM/dd/yyyy: ");
                String inputDOB = input.nextLine();

                Date dob = Utilities.dobFormat.parse(inputDOB);

                System.out.println("Is this a Business account (1) or a Regular account (2)?");
                int userType = Integer.parseInt(input.nextLine());


                if (appManager.signUp(username, password, dob, types[userType-1])) {
                    System.out.println("Sign Up Succesful!\n");
                    currentUser = appManager.getCurrentUser();
                }
                else
                    System.out.println("Something went wrong\nIs the username already taken?\n");
                break;
            case 2:
                System.out.println("Enter your username: ");
                username = input.nextLine();
                System.out.println("Enter your password: ");
                password = input.nextLine();
                
                if (!appManager.login(username, password))
                    System.out.println("Either the username or password was incorrect.");
                else
                    currentUser = appManager.getCurrentUser();

                System.out.println("\n-----------------------------------------------");
                
                break;
            case 3:
                System.out.print("Enter the departure city: ");
                String departure = input.nextLine();
                System.out.print("Enter the destination city: ");
                String destination = input.nextLine();
                appManager.searchFlights(departure,destination);
                break;
            case 4:
                System.out.print("Search Hotels: ");
                appManager.searchHotels(input.nextLine());
                break;
            case 5:
                break;
            case 6:
                appManager.logout();
                appManager.close();
                input.close();
                return false;
            default:
                break;
        }
        return true;
    }

    /**
     * This is the main menu for the registered user 
     * @param boolean this return true if the main menu is still running
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ParseException
     * @throws java.text.ParseException
     */
    public static boolean registeredMainMenu() throws FileNotFoundException, IOException, ParseException, java.text.ParseException {
        
        //cheapest flights

        RegisteredUser registeredUser = (RegisteredUser) currentUser;


        System.out.println(currentUser.menuString());

        int action = Integer.parseInt(input.nextLine());

        switch (action) {
            case 1:
                registeredUser.setPreferences();
                break;
            case 2:
                registeredUser.changePassword();
                break;
            case 3:
                break;
            case 4:
                System.out.println("Current Bookings: ");
                for (FlightBooking booking : registeredUser.getFlightBookings())
                    System.out.println(booking.toString()+"\n");
                for (HotelBooking booking : registeredUser.getHotelBookings())
                    System.out.println(booking.toString()+"\n");
                break;
            case 5:
                System.out.print("Enter the departure city: ");
                String departure = input.nextLine();
                System.out.print("Enter the destination city: ");
                String destination = input.nextLine();
                appManager.searchFlights(departure,destination);
                System.out.println("Pick the flight you would like to book, or 'back' ");
                String in = input.nextLine();
                if (!in.equalsIgnoreCase("back"))
                    if (!appManager.bookFlight(Integer.parseInt(in)))
                        System.out.println("Unable to book a seat or you backed out");
                break;
            case 6:
                System.out.print("Search Hotels: ");
                appManager.searchHotels(input.nextLine());
                System.out.println("Pick the hotel you would like to book, or 'back' ");
                String hot = input.nextLine();
                if(!hot.equalsIgnoreCase("back"));
                    if(!appManager.bookHotel(Integer.parseInt(hot)))
                        System.out.println("No more rooms or somethin");

                break;


            case 7:
                while (true) {
                    System.out.println("What is your friend's name?");
                    registeredUser.addFriends(input.nextLine());
                    System.out.println("Add another friend (y/n)?");
                    if (input.nextLine().equalsIgnoreCase("y"))
                        continue;
                    else
                        break;
                }
                break;
            case 8:
                System.out.println("Enter the name of the friend you would like to remove: ");
                System.out.println("Current friends are: ");
                for (GuestUser guest : registeredUser.getFriends()) {
                    System.out.println(guest.username);
                }
                registeredUser.removeFriend(input.nextLine());
                break;
            case 9:
                registeredUser.itinerary();
            case 10:
                appManager.logout();
                currentUser = appManager.getCurrentUser();
                break;
            default:
                break;
        }
        return true;
    }
    /**
     * This is the main menu for the business user 
     * @param boolean this return true if the main menu is still running
     */
    public static boolean businessMainMenu() {
   

        System.out.println(currentUser.menuString());

        int action = Integer.parseInt(input.nextLine());

        switch (action) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            default:
                break;
        }
        return true;
    }
    public static void main(String args[]) throws FileNotFoundException, IOException, ParseException, java.text.ParseException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {

        appManager = ApplicationManager.getInstance();
        Utilities.getInstance();

        while(run());
        
            
    }



}