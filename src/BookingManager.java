package src;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.json.simple.parser.ParseException;

public class BookingManager {

    private static BookingManager bookingManager;

    private static HashMap<UUID,Plane> planes; //We will store all the planes in this hashmap. The uuid will be the key.
    private static HashMap<UUID,Hotel> hotels; //We will store all the hotels in this hashmap. The uuid will be the key.


    private BookingManager() throws FileNotFoundException, IOException, ParseException, java.text.ParseException{

        planes = Utilities.loadPlanes(); //This should allow us to get the full list of planes from the JSON
        hotels = Utilities.loadHotels(); //This should allow us to get the full list of hotels from the JSON

    }

    public static BookingManager getInstance() throws FileNotFoundException, IOException, ParseException, java.text.ParseException {

        if (bookingManager == null)
            bookingManager = new BookingManager();

        return bookingManager;   

    }

    public ArrayList<Plane> getFlights(String search, int severity) {
        ArrayList<Plane> results = new ArrayList<Plane>();
        String[] searchWords = search.split(" ");
        for (Plane value : planes.values()) {//iterate through each archive in planes.
            int score = 0;
            for(int j=0;j<searchWords.length;j++) {//iterate through each word in the user's search.
                if(searchWords[j] == value.getUUID().toString())//checking for uuid.
                    score++;
                if(searchWords[j] == value.getAirline().toString())//checking for airline.
                    score++;
                if(doubleParser(searchWords[j]) != -1 && doubleParser(searchWords[j]) <= value.getAvailableSeats())//checking for available seats.
                    score++;
                //TODO check price (if single number, then check for exactly that one. If 2 numbers sepparated by a dash then search for middling numbers)
                //TODO differentiate from availableSeats.
                if(searchWords[j] == Double.toString(value.getPrice()))
                    score++;
                if(searchWords[j] == value.getDepartureDate().toString())//checking departureDate.
                    score++;
                if(searchWords[j] == value.getArrivalDate().toString())//checking arrivalDate.
                    score++;
                if(searchWords[j].equalsIgnoreCase(value.getDestinationCity()))//checking destinationCity.
                    score++;
                if(searchWords[j].equalsIgnoreCase(value.getDeparturenCity()))//checking departureCity.
                    score++;
                if(searchWords[j] == "smoking" && value.getSmoking() == true)//checking smoking.
                    score++;
                else if(searchWords[j] == "no-smoking" && value.getSmoking() == false)
                    score++;
                if(searchWords[j] == "pets" && value.getPetsAllowed() == true)//checking petsAllowed.
                    score++;
                else if(searchWords[j] == "no-pets" && value.getPetsAllowed() == false)
                    score++;
                }
            if(score >= severity)
                results.add(value);
        }
        if(results.size() <=1 && severity >=1)//add similar date keyword to search and run again and place extra results at the bottom.
            results = getFlights(search, severity--);
        else if(results.size() <=1 && severity == 0)
            System.out.println("You either typed it in wrong or didnt type in enough :/");
        return results;
    }

    public ArrayList<Hotel> getHotels(String search, int severity) {
        ArrayList<Hotel> results = new ArrayList<Hotel>();
        String[] searchWords = search.split(" ");

        for (Hotel value : hotels.values()) {//iterate through each entry in hotels.
            int score = 0;
            for(int j=0;j<searchWords.length;j++) {//iterate through each word in the user's search.
            if(searchWords[j] == value.getUUID().toString())//checking for uuid.
                score++;
            //TODO getHotel name
            if(doubleParser(searchWords[j]) != -1 && doubleParser(searchWords[j]) <= value.getAvailableRooms())//checking for available rooms.
                score++;
            //TODO check price (if single number, then check for exactly that one. If 2 numbers sepparated by a dash then search for middling numbers)
            //TODO differentiate from availableRooms.
            if(searchWords[j] == Double.toString(value.getPrice()))
                score++;
            if(searchWords[j].equalsIgnoreCase(value.getCity()))//checking Hotel City.
                score++;
            if(searchWords[j] == "smoking" && value.getSmoking() == true)//checking smoking.
                score++;
            else if(searchWords[j] == "no-smoking" && value.getSmoking() == false)
                score++;
            if(searchWords[j] == "pets" && value.getPetsAllowed() == true)//checking petsAllowed.
                score++;
            else if(searchWords[j] == "no-pets" && value.getPetsAllowed() == false)
                score++;
            }
            if(score >= severity)
                results.add(value);
        }
        if(results.size() <=1 && severity >=0) {//add broader location keyword to search and run again and place extra results at the bottom.

            results = getHotels(search, severity--);
        }
        else if(results.size() <=1 && severity == 0)
            System.out.println("You either typed it in wrong or didnt type in enough :/");
        return results;
    }
    private double doubleParser(String str) {
        double val;
        try {
            val = Double.parseDouble(str);
        }
        catch (NumberFormatException e) {
            val = -1;
        }
        return val;
    }
}
