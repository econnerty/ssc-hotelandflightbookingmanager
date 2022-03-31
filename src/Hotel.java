package src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;

import org.json.simple.JSONArray;
/**
 * this class holds the information for each hotel.
 */
public class Hotel implements src.JSON{

    private static final int FLOORS = 3; //Hard Coded Hotel Size
    private static final int LENGTH = 50; //Hard Coded Hotel size
    private static final int WIDTH = 2; //Hard Coded Hotel size

    private UUID uuid;
    

    private int availableRooms = FLOORS*LENGTH*WIDTH; //Max rooms available
    private Hotels hotel; //Enum for hotel
    private double price; //Price for each room in the Hotel

    private String city;
    private boolean smoking;
    private boolean petsAllowed;

    private double rating;
/**
 * arrayllist that holds the amenities
 */
    private ArrayList<Amenities> amenities = new ArrayList<>();

    private boolean[][][] rooms = new boolean[LENGTH][WIDTH][FLOORS]; //Keeps track of all the available rooms whether they are booked or not

/**
 * This method sets the uuid and clears the rooms in the hotel.
 * @param uuid the Universally unique identifier for the hotel.
 */
    public Hotel (UUID uuid){
        this.uuid = uuid;
        clearRooms();
    }
/**
 * This methods constructs the hotel and clears the rooms.
 * @param uuid the Universally unique identifier for the hotel.
 * @param availableRooms the amount of available rooms for the hotel.
 * @param hotel the enum for hotel.
 * @param price the price for the hotel.
 * @param city the city where the hotel is.
 * @param smoking whether smoking is allowed in the hotel.
 * @param petsAllowed whether pets are allowed in the hotel.
 * @param rating The hotel's rating.
 */
    public Hotel(UUID uuid, int availableRooms, Hotels hotel, double price, String city, boolean smoking, boolean petsAllowed, double rating) {
        this.uuid = uuid;
        this.availableRooms = availableRooms;
        this.hotel = hotel;
        this.price = price;
        this.city = city;
        this.smoking = smoking;
        this.petsAllowed = petsAllowed;
        this.rating = rating;
        clearRooms();
    }
/**
 * This methods constructs the hotel.
 * @param uuid the Universally unique identifier for the hotel.
 * @param availableRooms the amount of available rooms for the hotel.
 * @param hotel the enum for hotel.
 * @param price the price for the hotel.
 * @param city the city where the hotel is.
 * @param smoking whether smoking is allowed in the hotel.
 * @param petsAllowed whether pets are allowed in the hotel.
 * @param rating The hotel's rating.
 * @param amenities The hotel's available amenities.
 */
    public Hotel(UUID uuid, int availableRooms, Hotels hotel, double price, String city, boolean smoking, boolean petsAllowed, boolean[][][] rooms, double rating, ArrayList<Amenities> amenities) {
        this.uuid = uuid;
        this.availableRooms = availableRooms;
        this.hotel = hotel;
        this.price = price;
        this.city = city;
        this.smoking = smoking;
        this.petsAllowed = petsAllowed;
        this.rooms = rooms;
        this.rating = rating;
        this.amenities = amenities;
    }
    /**
     * This method books one of the hotel's rooms.
     * @param booking the room to be booked.
     * @return true/false depending on if a room is booked.
     */
    public boolean bookRoom(HotelBooking booking) {
        this.rooms[booking.getIndex()[0]][booking.getIndex()[1]][booking.getIndex()[2]] = true; //this needs to be fixed.
        availableRooms--;
        if (availableRooms < 0){
            availableRooms++;
            return false;
        }
        return true;
    }
/**
 * This method clears the rooms in the hotel.
 */
    private void clearRooms() {
        for (boolean[][] room : rooms)
            for(boolean[] room2 : room)
                Arrays.fill(room2, false);

        this.availableRooms = FLOORS*LENGTH*WIDTH;
    }
    /**
     * this method gets the amount of available rooms.
     * @return returns the amount of available rooms.
     */
    public int getAvailableRooms() {
        return this.availableRooms;
    }
    /**
     * this method gets the UUID
     * @return returns the UUID
     */
    public UUID getUUID() {
        return this.uuid;
    }
    /**
     * this method gets the name of hotel 
     * @return returns the name of the hotel
     */
    public String getName() {
        return this.hotel.name();
    }
    /**
     * this method sets the hotel 
     * @return returns the hotel
     */
    public void setHotel() {
        //this.hotel=hotel;
    }
    /**
     * this method sets the price of the hotel
     * @return returns the set hotel price
     */
    public void setPrice(Double price) {
        this.price=price;
    }
    /**
     * this method gets the price of the hotel
     * @return returns the price of the hotel
     */
    public double getPrice() {
        return this.price;
    }
    /**
     * this method sets the city of the hotel
     * @return returns the city that hotel is set in
     */
    public void setCity(String city) {
        this.city=city;
    }
    /**
     * this method gets the city of the hotel
     * @return returns the city that hotel is located in
     */
    public String getCity() {
        return this.city;
    }
    /**
     * this method sets if the hotel allows smoking
     * @param boolean determines true or false if the hotel accepts smoking
     * @return return if the hotel allows smoking
     */
    public void setSmoking(Boolean smoking) {
        this.smoking=smoking;
    }
    /**
     * this method gets if smoking is allowed
     * @return returns if smoking is allowed
     */
    public Boolean getSmoking() {
        return this.smoking;
    }
    /**
     * this method sets if the hotel allows pets
     * @param boolean determines true or false if the hotel accepts pets
     * @return return if the hotel allows pets
     */
    public void setPets(Boolean petsAllowed) {
        this.petsAllowed=petsAllowed;
    }
    /**
     * this method gets if pets are allowed
     * @return returns if pets are allowed
     */
    public Boolean getPetsAllowed() {
        return this.petsAllowed;
    }
    /**
     * this method gets the size of the hotel
     * @param int it number of floors, length and witdth 
     * @return this return the floors, length and width of the hotel
     */
    public static int[] getSize() {
        return new int[]{Hotel.FLOORS, Hotel.LENGTH, Hotel.WIDTH};
    }
    /**
     * this return the JSON object that can be written to a JSON file
     * @return this return the JSON object
     */
    public Map toJsonObject() {
        Map jsonObject = new LinkedHashMap<>();

        jsonObject.put("uuid", this.uuid.toString());
        jsonObject.put("availableRooms", this.availableRooms);
        jsonObject.put("hotel", this.hotel.toString());
        jsonObject.put("price", this.price);
        jsonObject.put("city",this.city);
        jsonObject.put("smoking", this.smoking);
        jsonObject.put("petsAllowed", this.petsAllowed);
        jsonObject.put("rating",this.rating);

        JSONArray jsonAmenities = new JSONArray();

        for (Amenities amen : amenities) {
            jsonAmenities.add(amen.toString());
        }
        jsonObject.put("amenities", jsonAmenities);
        return jsonObject;
    }
    /**
     * Returns a string of the hotel 
     * @return returns the string of the hotel
     */
    @Override
    public String toString() {
        return "Hotel [availableRooms=" + availableRooms + ", city=" + city + ", hotel=" + hotel + ", petsAllowed="
                + petsAllowed + ", price=" + price + ", rooms=" + Arrays.toString(rooms) + ", smoking=" + smoking
                + ", uuid=" + uuid + "]";
    }
    /**
     * Return a formatted string of the hotel info
     * @return this return the info of the hotel
     */
    public String getHotelInfo() {
        
        String ret = "\tHotel: " + hotel + "\tCity: " + city + "\tPrice: " + price+"\tAvailable Rooms: "
            +availableRooms+"\tSmoking Allowed: "+smoking+"\tPets Allowed: "+petsAllowed+"\tRating: "+rating+"/5." + "\tAmenities:\n\t ";

        for (Amenities amenity : amenities) {
            ret += amenity.name() + ", ";
        }
        return ret;
    }
    

    /*//TODO Print out all the available rooms, whether they are booked or not, and the index that they are located at
    public String toString() {
        return "";
    }*/
    
}
