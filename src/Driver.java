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

    private static boolean run(){

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
            case 5:
                break;
            case 6:
                break;
            default:
                break;
        }

        return false;
    }
    
    public static void main(String args[]) throws FileNotFoundException, IOException, ParseException, java.text.ParseException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {

        appManager = ApplicationManager.getInstance();

        while(run());
        
            
    }



}