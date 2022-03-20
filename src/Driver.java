package src;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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


}
