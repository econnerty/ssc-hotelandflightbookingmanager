package src;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;

import java.util.UUID;

public class Driver {

    private static String currentUser = "";

    private static boolean run(){

        return false;
    }
    
    public static void main(String args[]) throws FileNotFoundException, IOException, ParseException, java.text.ParseException {

        Utilities.getInstance(); //Call init on Utilities
        ApplicationManager.getInstance();

        while(run());

        

        
            
    }


}
