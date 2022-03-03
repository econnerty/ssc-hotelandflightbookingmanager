package src;

import java.util.*;
import java.security.*;
import java.io.*;
import java.nio.charset.StandardCharsets;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;



public class Utilities {

    private static final String USER_JSON_PATH = "data/users.json";
    private static final String PLANE_JSON_PATH = "data/planes.json";
    private static final String HOTEL_JSON_PATH = "data/hotels.json";
    
    private static final Random random = new SecureRandom();

    private static MessageDigest md;
    private static JSONParser JSONParser;

    private static Utilities utilities;

    private Utilities() {

        try {md = MessageDigest.getInstance("SHA-256");} 
        catch (NoSuchAlgorithmException e) {e.printStackTrace();}

        JSONParser = new JSONParser();

        System.out.println("Utils succesfully initialized");
    }

    public static Utilities getInstance() {
        
        if (utilities == null)
            Utilities.utilities = new Utilities();
        
        return Utilities.utilities;
        
    }

    public static String hashPassword(String password, String salt) {
        
        String hash = "";

        byte[] pass = new byte[password.getBytes().length+salt.getBytes().length];

        System.arraycopy(password.getBytes(), 0, pass, 0, password.getBytes().length);
        System.arraycopy(salt.getBytes(), 0, pass, password.getBytes().length, salt.getBytes().length);

        byte[] bytes = md.digest(pass);

        for (byte b :bytes)
            hash += String.format("%02x", b);

        return hash;
    }

    public static HashMap<String, Users> loadUsers(){
        return new HashMap<String,Users>();
    }

    public static HashMap<String, Plane> loadPlanes(){
        return new HashMap<String,Plane>();
    }

    public static HashMap<String, Hotel> loadHotels(){
        return new HashMap<String,Hotel>();
    }
    
}
