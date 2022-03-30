package src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;

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

    private ArrayList<Amenities> amenities = new ArrayList<>();

    private boolean[][][] rooms = new boolean[LENGTH][WIDTH][FLOORS]; //Keeps track of all the available rooms whether they are booked or not


    public Hotel (UUID uuid){
        this.uuid = uuid;
        clearRooms();
    }

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
    public void bookRoom(HotelBooking booking) {
        
        this.rooms[booking.getIndex()[0]][booking.getIndex()[1]][booking.getIndex()[2]] = true; //this needs to be fixed.
        availableRooms--;
    }

    private void clearRooms() {
        for (boolean[][] room : rooms)
            for(boolean[] room2 : room)
                Arrays.fill(room2, false);

        this.availableRooms = FLOORS*LENGTH*WIDTH;
    }
    public int getAvailableRooms() {
        return this.availableRooms;
    }

    public UUID getUUID() {
        return this.uuid;
    }

    public void setHotel() {
        //this.hotel=hotel;
    }

    public void setPrice(Double price) {
        this.price=price;
    }
    public double getPrice() {
        return this.price;
    }

    public void setCity(String city) {
        this.city=city;
    }
    public String getCity() {
        return this.city;
    }

    public void setSmoking(Boolean smoking) {
        this.smoking=smoking;
    }
    public Boolean getSmoking() {
        return this.smoking;
    }

    public void setPets(Boolean petsAllowed) {
        this.petsAllowed=petsAllowed;
    }
    public Boolean getPetsAllowed() {
        return this.petsAllowed;
    }

    public static int[] getSize() {
        return new int[]{Hotel.FLOORS, Hotel.LENGTH, Hotel.WIDTH};
    }

    public Map toJsonObject() {
        return null;
    }

    @Override
    public String toString() {
        return "Hotel [availableRooms=" + availableRooms + ", city=" + city + ", hotel=" + hotel + ", petsAllowed="
                + petsAllowed + ", price=" + price + ", rooms=" + Arrays.toString(rooms) + ", smoking=" + smoking
                + ", uuid=" + uuid + "]";
    }
    public String getHotelInfo() {
        
        return "\tHotel: " + hotel + "\tCity: " + city + "\tPrice: " + price+"\tAvailable Rooms: "
            +availableRooms+"\tSmoking Allowed: "+smoking+"\tPets Allowed: "+petsAllowed+"\tRating: "+rating+"/5.";
    }
    

    /*//TODO Print out all the available rooms, whether they are booked or not, and the index that they are located at
    public String toString() {
        return "";
    }*/
    
}
