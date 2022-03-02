package java;

import java.util.UUID;

public class Hotel {

    private static final int FLOORS = 6; //Hard Coded Hotel Size
    private static final int LENGTH = 50; //Hard Coded Hotel size
    private static final int WIDTH = 2; //Hard Coded Hotel size

    private String uuid;
    

    private int availableRooms = FLOORS*LENGTH*WIDTH; //Max seats available
    private Hotels hotel; //Enum for airlines
    private double price; //Price for each seat on the Hotel. This can be expanded later for different classes

    private boolean[][][] rooms = new boolean[LENGTH][WIDTH][FLOORS]; //Keeps track of all the available rooms whether they are booked or not

    //TODO Print out all the available rooms, whether they are booked or not, and the index that they are located at
    public String toString() {
        return "";
    }
    
}
