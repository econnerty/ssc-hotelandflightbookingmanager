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

public class Driver {

    private static User currentUser = new GuestUser();
    private static ApplicationManager appManager;

    private static final String[] types = {"Business", "Registered"};

    private static final Scanner input = new Scanner(System.in);

    private static boolean run() throws java.text.ParseException, IOException{

        if (currentUser.getClass() == GuestUser.class)
            return mainMenu();
        else if (currentUser.getClass() == BusinessUser.class)
            return businessMainMenu();
        else if (currentUser.getClass() == RegisteredUser.class)
            return registeredMainMenu();



        return false;
    }

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
                System.out.print("Search Flights: ");
                appManager.searchFlights(input.nextLine());
                break;
            case 4:
                System.out.print("Search Hotels: ");
                appManager.searchHotels(input.nextLine());
                break;
            case 5:
                break;
            case 6:
                appManager.logout();
                break;
            case 7:
                appManager.close();
                return false;
            default:
                break;
        }
        return true;
    }


    public static boolean registeredMainMenu() {
        
        //cheapest flights

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

    public static boolean businessMainMenu() {
        //cheapest flights

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
        //Utilities.generateFlights();

        while(run());
        
            
    }



}