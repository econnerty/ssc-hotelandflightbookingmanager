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

    private String[] layovers;

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
        String departureCity, String destinationCity,boolean smoking, boolean petsAllowed, boolean[][] seats, String[] layovers) {

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
        this.layovers = layovers;
    }

    public void bookSeat(FlightBooking booking) {
        
        this.seats[booking.getIndex()[0]][booking.getIndex()[1]] = true;
        availableSeats--;
    }

    public String getDestinationCity(){
        return this.destinationCity;
    }

    private void clearSeats() {
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

    public void setdestinationCity(String city) {
        this.destinationCity = city;
    }
    
    public void setdepartureCity(String city) {
        this.departureCity = city;
    }
    
    public void setDepartureDate(Date date) {
        this.departureDate = date;
    }

    public void setArrivalDate(Date date) {
        this.arrivalDate = date;
    }

    public int getLayoversCount(){
        return this.layovers.length;
    }

    public String[] getLayovers() {
        return this.layovers;
    }

    public static int[] getSize() {
        return new int[]{Plane.SEAT_COLUMNS, Plane.SEAT_ROWS};
    }

    public Map toJsonObject() {
        return null;
    }


    //TODO This is just for debugging
    @Override
    public String toString() {
        return "Plane [airline=" + airline + ", arrivalDate=" + arrivalDate + ", availableSeats=" + availableSeats
                + ", departureCity=" + departureCity + ", departureDate=" + departureDate + ", destinationCity="
                + destinationCity + ", petsAllowed=" + petsAllowed + ", price=" + price + ", seats="
                + Arrays.toString(seats) + ", smoking=" + smoking + ", uuid=" + uuid + "]";
    }

    public String getFlightInfo() {
        String sAirline;
        String date;
        String departure;
        String destination;
        return "\tAirline: " + airline + "\tArrival Date: " + arrivalDate + "\tDeparture City: " + departureCity + "\tDestination: " + destinationCity + "\tPrice: " + price;
    }

    public void printSeats() {
        int count = 1;
        
        String out = "";
        
        System.out.println("    A         B                 D          E         ");

        for (int i = 0; i < seats.length; i++){
            int j = 0;
            //out += count + " ";
            if (count < 10)
                out += " ";
            while (j < seats[0].length/2){
               
                out += "[ " + "("+seats[i][j] + ")"+ " ]";
                j++;
            }
            out += "       |       ";
            while (j < seats[0].length){
                
                out += "\t[ " + "("+seats[i][j] + ")"+ " ]";
                j++;
            }
            out += "\n";
            count++;
        }

        System.out.println(out);

    }

}


    
