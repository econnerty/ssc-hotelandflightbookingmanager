package src;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;

import java.util.UUID;

public class Driver {

    private static Users currentUser;
    private static ApplicationManager appManager;

    private static boolean run(){

        //TODO
        //Do the stuff
        return false;
    }
    
    public static void main(String args[]) throws FileNotFoundException, IOException, ParseException, java.text.ParseException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {

        appManager = ApplicationManager.getInstance();

        //Utilities.generateFlights();

        while(run());

        
            
    }


}
