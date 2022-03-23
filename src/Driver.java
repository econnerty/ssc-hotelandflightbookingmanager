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

    private static final Scanner input = new Scanner(System.in);

    private static String[] types = {"Business", "Registered"};

    private static boolean run() throws java.text.ParseException, IOException{

        if (currentUser.getClass() == GuestUser.class)
            mainMenu();
        else if (currentUser.getClass() == BusinessUser.class)
            businessMainMenu();
        else if (currentUser.getClass() == RegisteredUser.class)
            registeredMainMenu();



        return true;
    }

    public static void mainMenu() throws IOException, java.text.ParseException {
        //cheapest flights

        System.out.println(currentUser.menuString());

        int action = Integer.parseInt(input.nextLine())6;


        switch (action) {
            case 1:
                System.out.println("Please enter your desired username: ");
                String username = input.nextLine();

                System.out.println("Please enter your desired password: ");
                String password = input.nextLine();

                System.out.println("Please enter your DOB in the format MM/dd/yyyy: ");
                String inputDOB = input.nextLine();

                Date dob = Utilities.dobFormat.parse(inputDOB);

                System.out.println("Is this a Business account (1) or a Regular account (2)?");
                int userType = Integer.parseInt(input.nextLine());


                String output = (appManager.signUp(username, password, dob, types[userType-1])) ? "Sign Up Succesful!" : "Something went wrong";
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                appManager.logout();
                break;
            case 7:
                appManager.close();
            default:
                break;
        }
    }


    public static void registeredMainMenu() {
        
        //cheapest flights

        System.out.println(currentUser.menuString());

        int action = input.nextInt();

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
    }

    public static void businessMainMenu() {
        Scanner input = new Scanner(System.in);
        //cheapest flights

        System.out.println(currentUser.menuString());

        int action = input.nextInt();

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
    }
    
    public static void main(String args[]) throws FileNotFoundException, IOException, ParseException, java.text.ParseException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {

        appManager = ApplicationManager.getInstance();
        Utilities.getInstance();

        while(run());
        
            
    }



}