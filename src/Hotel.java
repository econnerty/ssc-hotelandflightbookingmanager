package src;

import java.util.Arrays;
import java.util.UUID;

public class Hotel {

    private static final int FLOORS = 6; //Hard Coded Hotel Size
    private static final int LENGTH = 50; //Hard Coded Hotel size
    private static final int WIDTH = 2; //Hard Coded Hotel size

    private UUID uuid;
    

    private int availableRooms = FLOORS*LENGTH*WIDTH; //Max rooms available
    private Hotels hotel; //Enum for hotel
    private double price; //Price for each room in the Hotel

    private String city;
    private boolean smoking;
    private boolean petsAllowed;

    private boolean[][][] rooms = new boolean[LENGTH][WIDTH][FLOORS]; //Keeps track of all the available rooms whether they are booked or not


    public Hotel (UUID uuid){
        this.uuid = uuid;
        clearRooms();
    }

    public Hotel(UUID uuid, int availableRooms, Hotels hotel, double price, String city, boolean smoking, boolean petsAllowed) {
        
        this.uuid = uuid;
        this.availableRooms = availableRooms;
        this.hotel = hotel;
        this.price = price;
        this.city = city;
        this.smoking = smoking;
        this.petsAllowed = petsAllowed;
        clearRooms();

    }

    public Hotel(UUID uuid, int availableRooms, Hotels hotel, double price, String city, boolean smoking, boolean petsAllowed, boolean[][][] rooms) {

        this.uuid = uuid;
        this.availableRooms = availableRooms;
        this.hotel = hotel;
        this.price = price;
        this.city = city;
        this.smoking = smoking;
        this.petsAllowed = petsAllowed;
        this.rooms = rooms;

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

    public void setHotel() {
        this.hotel=hotel;
    }

    public void setPrice() {
        this.price=price;
    }

    public void setCity() {
        this.city=city;
    }

    public void setSmoking() {
        this.smoking=smoking;
    }

    public void setPets() {
        this.petsAllowed=petsAllowed;
    }

    public static int[] getSize() {
        return new int[]{Hotel.FLOORS, Hotel.LENGTH, Hotel.WIDTH};
    }

    //TODO Print out all the available rooms, whether they are booked or not, and the index that they are located at
    public String toString() {
        return "";
    }
    
}
