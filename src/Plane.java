package src;

import java.util.UUID;

public class Plane {

    private static final int SEAT_COLUMNS = 6; //Hard Coded Plane Size
    private static final int SEAT_ROWS = 50; //Hard Coded plane size

    private String uuid;

    private int availableSeats = SEAT_COLUMNS*SEAT_ROWS; //Max seats available
    private Airlines airline; //Enum for airlines
    private double price; //Price for each seat on the plane. This can be expanded later for different classes

    private boolean[][] seats = new boolean[SEAT_ROWS][SEAT_COLUMNS]; //Keeps track of all the available seats whether they are booked or not



    //TODO Print out all the available seats, whether they are booked or not, and the index that they are located at
    public String toString() {
        return "";
    }

}


    
