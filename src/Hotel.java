package src;

import java.util.UUID;

public class Hotel {

    private static final int FLOORS = 6; //Hard Coded Hotel Size
    private static final int LENGTH = 50; //Hard Coded Hotel size
    private static final int WIDTH = 2; //Hard Coded Hotel size

    private UUID uuid;
    

    private int availableRooms = FLOORS*LENGTH*WIDTH; //Max rooms available
    private Hotels hotel; //Enum for hotel
    private double price; //Price for each room in the Hotel

    private boolean[][][] rooms = new boolean[LENGTH][WIDTH][FLOORS]; //Keeps track of all the available rooms whether they are booked or not


    public void bookRoom(HotelBooking booking) {
        
        this.rooms[booking.getIndex()[0]][booking.getIndex()[1]][booking.getIndex()[2]] = true; //this needs to be fixed.
        availableRooms--;
    }

    //TODO Print out all the available rooms, whether they are booked or not, and the index that they are located at
    public String toString() {
        return "";
    }
    
}
