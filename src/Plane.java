package src;

import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONArray;

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

    public boolean bookSeat(FlightBooking booking) {
        if (this.seats[booking.getIndex()[0]][booking.getIndex()[1]] == true)
            return false;
        this.seats[booking.getIndex()[0]][booking.getIndex()[1]] = true;
        this.availableSeats--;
        return true;
    }

    public String getDestinationCity(){
        return this.destinationCity;
    }
    public String getDepartureCity(){
        return this.departureCity;
    }


    private void clearSeats() {
        for (boolean[] seat : seats) 
            Arrays.fill(seat, false);

        this.availableSeats = SEAT_COLUMNS*SEAT_ROWS;
    }

    public UUID getUUID() {
        return this.uuid;
    }

    public Date getDepartureDate() {
        return this.departureDate;
    }
    public Date getArrivalDate() {
        return this.arrivalDate;
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
        return new int[]{Plane.SEAT_ROWS, Plane.SEAT_COLUMNS};
    }

    public Map toJsonObject() {
        
        Map jsonObject = new LinkedHashMap<>();

        jsonObject.put("uuid", this.uuid.toString());
        jsonObject.put("airline", this.airline.toString());
        jsonObject.put("availableSeats", this.availableSeats);
        jsonObject.put("departureCity", this.departureCity);
        jsonObject.put("destinationCity",this.destinationCity);
        jsonObject.put("departureDate", this.departureDate.toString());
        jsonObject.put("arrivalDate", this.arrivalDate.toString());

        JSONArray jsonLayovers = new JSONArray();

        for (String layover : layovers) {
            jsonLayovers.add(layover);
        }

        jsonObject.put("layovers", jsonLayovers);
        jsonObject.put("price", this.price);
        jsonObject.put("smoking", this.smoking);
        jsonObject.put("petsAllowed", this.petsAllowed);
        JSONArray jsonSeats = new JSONArray();
            for (int p = 0; p < this.seats.length; p++) {
                JSONArray arr = new JSONArray();
                for (int j = 0; j < this.seats[p].length; j++) {
                    if (seats[p][j] == true)
                        arr.add(1);
                    else
                        arr.add(0);
                }
                jsonSeats.add(arr);
            }
            jsonObject.put("seats", jsonSeats);
            

        return jsonObject;
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
        String layovers = "";

        if (this.layovers != null)
            for (String temp : this.layovers)
                layovers += temp + ", ";
        long flightTime = arrivalDate.getTime() - departureDate.getTime();
        long longMinutes= flightTime/60000;
        int hours = Math.toIntExact(longMinutes/60);
        int minutes = Math.toIntExact(longMinutes%60);
        return "\tAirline: " + airline + "\tDeparture Date: " + departureDate + "\tDeparture City: " + departureCity + "\tDestination: " + destinationCity +  "\tPrice: " + price +  "\tSeats Available: "+ availableSeats + "\tFlight Time: " + hours + " Hours and " + minutes + " minutes\n\tLayovers: "+layovers;
    }

    public void printSeats() {
        String out = "";
        int N = 1;
        System.out.println("    A      B      C      D ");
        for(int row=0;row<seats.length;row++) {
            for(int col=0;col<seats[row].length;col++) {
                if(col==0&&N<10) {
                    out+=(N+"   ");
                    N++;
                }
                else if(col==0&&N>=10) {
                    out+=(N+"  ");
                    N++;
                }
                if(seats[row][col]==true) {
                    out+=("+"+"      ");
                }
                else {
                    out+=("-"+"      ");
                }
            }
            out+="\n";
        }
        System.out.println(out);
    }

}


    
