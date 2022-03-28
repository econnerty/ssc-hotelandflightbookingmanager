package src;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class Plane implements src.JSON{

    private static final int SEAT_COLUMNS = 4; //Hard Coded Plane Size
    private static final int SEAT_ROWS = 20; //Hard Coded plane size

    private UUID uuid;

    private Airlines airline; //Enum for airlines
    private int availableSeats = SEAT_COLUMNS*SEAT_ROWS; //Max seats available
    private double price; //Price for each seat on the plane. This can be expanded later for different classes

    private Date departureDate;
    private Date arrivalDate;

    private String destinationCity;
    private String departureCity;

    private boolean smoking;
    private boolean petsAllowed;

    private boolean[][] seats = new boolean[SEAT_ROWS][SEAT_COLUMNS]; //Keeps track of all the available seats whether they are booked or not


    public Plane(UUID uuid) {
        this.uuid = uuid;
        clearSeats();
    }

    public Plane(UUID uuid, Airlines airline, int availableSeats, double price, Date departureDate, Date arrivalDate, 
    String departureCity, String destinationCity) {

    this.uuid = uuid;
    this.airline = airline;
    this.availableSeats = availableSeats;
    this.price = price;
    this.departureDate = departureDate;
    this.arrivalDate = arrivalDate;
    this.destinationCity = destinationCity;
    this.departureCity = departureCity;
    clearSeats();
}

    public Plane(UUID uuid, Airlines airline, int availableSeats, double price, Date departureDate, Date arrivalDate, 
        String departureCity, String destinationCity,boolean smoking, boolean petsAllowed, boolean[][] seats) {

        this.uuid = uuid;
        this.airline = airline;
        this.availableSeats = availableSeats;
        this.price = price;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.destinationCity = destinationCity;
        this.departureCity = departureCity;
        this.seats = seats;
        this.smoking = smoking;
        this.petsAllowed = petsAllowed;
    }
    public UUID getUUID() {
        return this.uuid;
    }
    public void bookSeat(FlightBooking booking) {
        
        this.seats[booking.getIndex()[0]][booking.getIndex()[1]] = true;
        availableSeats--;
    }

    private void clearSeats() {
        for (boolean[] seat : seats) 
            Arrays.fill(seat, false);

        this.availableSeats = SEAT_COLUMNS*SEAT_ROWS;
    }
    public void setAirline(Airlines airline) {
        this.airline = airline;
    }
    public Airlines getAirline() {
        return this.airline;
    }

    public int getAvailableSeats() {
        return this.availableSeats;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
    public double getPrice() {
        return this.price;
    }

    public void setDestinationCity(String city) {
        this.destinationCity = city;
    }
    public String getDestinationCity() {
        return this.destinationCity;
    }
    
    public void setdepartureCity(String city) {
        this.departureCity = city;
    }
    public String getDeparturenCity() {
        return this.departureCity;
    }
    
    public void setDepartureDate(Date date) {
        this.departureDate = date;
    }
    public Date getDepartureDate() {
        return this.departureDate;
    }

    public void setArrivalDate(Date date) {
        this.arrivalDate = date;
    }
    public Date getArrivalDate() {
        return this.arrivalDate;
    }

    public void setSmoking(Boolean smoking) {
        this.smoking = smoking;
    }
    public Boolean getSmoking() {
        return this.smoking;
    }

    public void setPetsAllowed(Boolean petsAllowed) {
        this.petsAllowed = petsAllowed;
    }
    public Boolean getPetsAllowed() {
        return this.petsAllowed;
    }
    
    public static int[] getSize() {
        return new int[]{Plane.SEAT_COLUMNS, Plane.SEAT_ROWS};
    }

    public Map toJsonObject() {
        return null;
    }


    //TODO Print all the plane's seats and whether they are booked.
    @Override
    public String toString() {
        return "Plane [airline=" + airline + ", arrivalDate=" + arrivalDate + ", availableSeats=" + availableSeats
                + ", departureCity=" + departureCity + ", departureDate=" + departureDate + ", destinationCity="
                + destinationCity + ", petsAllowed=" + petsAllowed + ", price=" + price + ", seats="
                + Arrays.toString(seats) + ", smoking=" + smoking + ", uuid=" + uuid + "]";
    }

}


    
