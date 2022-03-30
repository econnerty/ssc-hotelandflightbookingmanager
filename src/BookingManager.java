package src;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.UUID;

import org.json.simple.parser.ParseException;

public class BookingManager {

    private static BookingManager bookingManager;

    private static HashMap<UUID,Plane> planes; //We will store all the planes in this hashmap. The uuid will be the key.
    private static HashMap<UUID,Hotel> hotels; //We will store all the hotels in this hashmap. The uuid will be the key.

    private static HashMap<UUID, Business> businesses;

    private static ArrayList<Plane> searchResults;


    private BookingManager() throws FileNotFoundException, IOException, ParseException, java.text.ParseException{

        planes = Utilities.loadPlanes(); //This should allow us to get the full list of planes from the JSON
        hotels = Utilities.loadHotels(); //This should allow us to get the full list of hotels from the JSON
        businesses = Utilities.loadBusinesses();


    }

    public static BookingManager getInstance() throws FileNotFoundException, IOException, ParseException, java.text.ParseException {

        if (bookingManager == null)
            bookingManager = new BookingManager();

        return bookingManager;   

    }

    public void searchFlights(String search) {
        System.out.println("Flight searches for: \""+search+"\"");
        searchResults = bookingManager.getFlights(search);
        int i = 1;
        for(Plane plane : searchResults){
            System.out.println(i + plane.getFlightInfo());
            i++;
        }
    }

    public void bookFlight(int choice) throws ParseException, java.text.ParseException, FileNotFoundException, IOException{
        System.out.println("Choose a seat in the format for you and each of your guests (A4, A5): ");

        searchResults.get(choice-1).printSeats();
        Scanner input = new Scanner(System.in);
        


        RegisteredUser registeredUser = (RegisteredUser) UserManager.getInstance().getCurrentUser();

        String seats[] = new String[registeredUser.getFriends().size()+1];

        for (int i = 0; i < seats.length; i++){
            if (i == 0) {
                System.out.println("Please enter the seat for yourself in the format (A1): ");
                seats[i] = input.nextLine();
            }
            else {
                System.out.println("Please enter the seat for " +registeredUser.getFriends().get(i-1).username + " in the format (A2): ");
                seats[i] = input.nextLine();
            }
        }

        for (String seat : seats){
            switch (seat.charAt(0)) {
                case 'A':
                    int[] index = {1, Integer.parseInt(String.valueOf(seat.charAt(1)))};
                    registeredUser.addFlightBooking(new FlightBooking(searchResults.get(choice-1).getUUID(), index));
                    break;
                case 'B':
                    int[] index2 = {1, Integer.parseInt(String.valueOf(seat.charAt(1)))};
                    registeredUser.addFlightBooking(new FlightBooking(searchResults.get(choice-1).getUUID(), index2));
                    break;
                case 'C':
                    int[] index3 = {1, Integer.parseInt(String.valueOf(seat.charAt(1)))};
                    registeredUser.addFlightBooking(new FlightBooking(searchResults.get(choice-1).getUUID(), index3));
                    break;
                case 'D':
                    int[] index4 = {1, Integer.parseInt(String.valueOf(seat.charAt(1)))};
                    registeredUser.addFlightBooking(new FlightBooking(searchResults.get(choice-1).getUUID(), index4));
                    break;
                default:
                    break;

            }
            try {
                UserManager.getInstance().updateUser(registeredUser);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public HashMap<UUID,Plane> getPlanes(){
        return this.planes;
    }
    public HashMap<UUID, Hotel> getHotels(){
        return this.hotels;
    }

    public ArrayList<Plane> getFlights(String search) {

        search = search.toLowerCase();

        String query[] = search.split(" ");
        ArrayList<Plane> results = new ArrayList<Plane>();
        if(search == "" || search == " ")
            return results;
        for (Plane plane : planes.values()) {//iterate through each archive in planes.
            if (plane.getDestinationCity().toLowerCase().contains(search)|| search.equalsIgnoreCase(plane.getDestinationCity())){
                results.add(plane);
            }

        }
 
        return results;
    }

    public ArrayList<Hotel> getHotels(String search) {
        search = search.toLowerCase();
        ArrayList<Hotel> results = new ArrayList<Hotel>();
        if(search == "" || search == " ")
            return results;
        for (Hotel hotel : hotels.values()) {//iterate through each archive in planes.
            if (hotel.getCity().toLowerCase().contains(search)){
                results.add(hotel);
            }
        }
        return results;
    }
    private double doubleParser(String str) {
        double val;
        try {
            val = Double.parseDouble(str);
        }
        catch (NumberFormatException e) {
            val = -1;
        }
        return val;
    }
}
