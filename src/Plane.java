package src;

import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

public class Plane {

    private static final int SEAT_COLUMNS = 6; //Hard Coded Plane Size
    private static final int SEAT_ROWS = 50; //Hard Coded plane size

    private UUID uuid;

    private int availableSeats = SEAT_COLUMNS*SEAT_ROWS; //Max seats available
    private Airlines airline; //Enum for airlines
    private double price; //Price for each seat on the plane. This can be expanded later for different classes

    private Date departureDate;
    private Date arrivalDate;

    private String departureCity;
    private String arrivalCity;

    private boolean[][] seats = new boolean[SEAT_ROWS][SEAT_COLUMNS]; //Keeps track of all the available seats whether they are booked or not


    public void bookSeat(int[] index) {
        this.seats[index[0]][index[1]] = true;
        availableSeats--;
    }

    public void clearSeats() {
        for (boolean[] seat : seats) 
            Arrays.fill(seat, false);

        this.availableSeats = SEAT_COLUMNS*SEAT_ROWS;
    }

    public void setAirline(Airlines airline) {
        this.airline = airline;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setDepartureCity(String city) {
        this.departureCity = city;
    }
    
    public void setArrivalCity(String city) {
        this.arrivalCity = city;
    }
    
    public void departureDate(Date date) {
        this.departureDate = date;
    }

    public void arrivalDate(Date date) {
        this.arrivalDate = date;
    }


    //TODO Print out all the available seats, whether they are booked or not, and the index that they are located at
    public String toString() {
        return "";
    }

}


    
