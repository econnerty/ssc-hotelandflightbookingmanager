package src;

import java.util.*;
import java.security.*;
import java.io.*;

import java.text.SimpleDateFormat;

import org.javatuples.Pair;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



public class Utilities {

    private static final String USER_JSON_PATH = "data/users.json";
    private static final String PLANE_JSON_PATH = "data/planes.json";
    private static final String HOTEL_JSON_PATH = "data/hotels.json";
    private static final String DATE_FORMAT = "MM/dd/yyyy' 'HH:mm:ss'Z'";

    private static SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);

    private static MessageDigest md;
    private static JSONParser JSONParser;

    private static Utilities utilities;

    private Utilities() {

        try {md = MessageDigest.getInstance("SHA-256");} 
        catch (NoSuchAlgorithmException e) {e.printStackTrace();}

        JSONParser = new JSONParser();
    }

    public static Utilities getInstance() {
        
        if (utilities == null)
            Utilities.utilities = new Utilities();
        
        return Utilities.utilities;
        
    }

    public static HashMap<String, Users> loadUsers() throws FileNotFoundException, IOException, ParseException, java.text.ParseException{

        JSONArray jsonArray = (JSONArray) JSONParser.parse(new FileReader(USER_JSON_PATH));
        HashMap<String, Users> users = new HashMap<>();

        for (Object object : jsonArray) {

            JSONObject jsonObject = (JSONObject) object;

            //UUID uuid = UUID.fromString(jsonObject.get("uuid").toString());
            String name = jsonObject.get("username").toString();
            String password = jsonObject.get("password").toString();
            Date creationDate = format.parse(jsonObject.get("creationDate").toString());
            String type = jsonObject.get("type").toString();          


            if (type.equalsIgnoreCase("registered")) {

                JSONArray jsonFlightBookings = (JSONArray) jsonObject.get("flightBookings");
                JSONArray jsonHotelBookings = (JSONArray) jsonObject.get("hotelBookings");
                
                Pair<String, int[]>[] flightBookings;
                Pair<String, int[]>[] hotelBookings;

                for(int i = 0; i < jsonFlightBookings.size(); i+=2) { //the way the json is stored, we have to jump forward by 2 to get to the next uuid
                    
                }

                for(int i = 0; i < jsonHotelBookings.size(); i+=3) { //the way the json is stored, we have to jump forward by 3 to get to the next uuid
                    
                }


            }
            else if (type.equalsIgnoreCase("business")) {

                JSONArray jsonAirlines = (JSONArray) jsonObject.get("airlines");
                JSONArray jsonHotels = (JSONArray) jsonObject.get("hotels");
                Airlines[] airlines = new Airlines[jsonAirlines.size()];
                Hotels[] hotels = new Hotels[jsonHotels.size()];

                for(int i = 0; i < jsonAirlines.size(); i++) {
                    airlines[i] = Airlines.valueOf(jsonAirlines.get(i).toString());
                }

                for(int i = 0; i < jsonHotels.size(); i++) {
                    hotels[i] = Hotels.valueOf(jsonHotels.get(i).toString());
                }

                users.put(name, new BusinessUser(name, password, creationDate, airlines, hotels));
            }

            

            
        }

        for (String key : users.keySet())
            System.out.println(key);

        return users;
    }

    public static HashMap<String, Plane> loadPlanes(){
        return new HashMap<String,Plane>();
    }

    public static HashMap<String, Hotel> loadHotels(){
        return new HashMap<String,Hotel>();
    }
    
}
