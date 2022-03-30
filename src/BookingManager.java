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
    private static ArrayList<Hotel> searchResultsHotel;


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
    
    public void searchHotels(String search) {
        System.out.println("Flight searches for: \""+search+"\"");
        searchResultsHotel = bookingManager.getHotels(search);
        int i = 1;
        for(Hotel hotel : searchResultsHotel){
            System.out.println(i + hotel.getHotelInfo());
            i++;
        }
    }

    public boolean bookFlight(int choice) throws ParseException, java.text.ParseException, FileNotFoundException, IOException{

        if (choice >= searchResults.size()){
            System.out.println("Invalid Choice");
            return false;
        }
        System.out.println("Choose a seat in the format for you and each of your guests (A4, A5): ");


        searchResults.get(choice-1).printSeats(); //its minus one because the user will input 1 but the index is zero
        Scanner input = new Scanner(System.in);
        


        RegisteredUser registeredUser = (RegisteredUser) UserManager.getInstance().getCurrentUser();

        String seats[] = new String[registeredUser.getFriends().size()+1];

        for (int i = 0; i < seats.length; i++){
            if (i == 0) {
                System.out.println("Please enter the seat for yourself in the format (A1) or back to back out: ");
                seats[i] = input.nextLine();
            }
            else {
                System.out.println("Please enter the seat for " +registeredUser.getFriends().get(i-1).username + " in the format (A2) or back to back out: ");
                seats[i] = input.nextLine();
            }
        }

        for (String read : seats) {
            if (read.equalsIgnoreCase("back"))
                return false;
        }

        for (String seat : seats){
            FlightBooking booking;
            Plane plane;
            switch (seat.charAt(0)) {
                case 'A':
                    int[] index = {0, Integer.parseInt(String.valueOf(seat.charAt(1)))-1};
                    booking = new FlightBooking(searchResults.get(choice-1).getUUID(), index);
                    plane = searchResults.get(choice-1);
                    if (!plane.bookSeat(booking))
                        return false;
                    addPlane(plane);
                    registeredUser.addFlightBooking(booking);
                    break;
                case 'B':
                    int[] index2 = {1, Integer.parseInt(String.valueOf(seat.charAt(1)))-1};
                    booking = new FlightBooking(searchResults.get(choice-1).getUUID(), index2);
                    plane = searchResults.get(choice-1);
                    if (!plane.bookSeat(booking))
                        return false;
                    addPlane(plane);
                    registeredUser.addFlightBooking(booking);
                    break;
                case 'C':
                    int[] index3 = {2, Integer.parseInt(String.valueOf(seat.charAt(1)))-1};
                    booking = new FlightBooking(searchResults.get(choice-1).getUUID(), index3);
                    plane = searchResults.get(choice-1);
                    if (!plane.bookSeat(booking))
                        return false;
                    addPlane(plane);
                    registeredUser.addFlightBooking(booking);
                    break;
                case 'D':
                    int[] index4 = {3, Integer.parseInt(String.valueOf(seat.charAt(1)))-1};
                    booking = new FlightBooking(searchResults.get(choice-1).getUUID(), index4);
                    plane = searchResults.get(choice-1);
                    if (!plane.bookSeat(booking))
                        return false;
                    addPlane(plane);
                    registeredUser.addFlightBooking(booking);
                    break;
                default:
                    break;

            }

        }
        try {
            UserManager.getInstance().updateUser(registeredUser);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean bookHotel(int choice) throws ParseException, java.text.ParseException, FileNotFoundException, IOException{

        if (choice >= searchResultsHotel.size()){
            System.out.println("Invalid Choice");
            return false;
        }
        //System.out.println("Choose a seat in the format for you and each of your guests (A4, A5): ");


        //searchResults.get(choice-1).printSeats(); //its minus one because the user will input 1 but the index is zero
        


        RegisteredUser registeredUser = (RegisteredUser) UserManager.getInstance().getCurrentUser();

        Hotel hotel = searchResultsHotel.get(choice-1);

        hotel.bookRoom(new HotelBooking(searchResultsHotel.get(choice-1).getUUID(), new int[]{0,0,0}));
        addHotel(hotel);

        registeredUser.addHotelBooking(new HotelBooking(searchResultsHotel.get(choice-1).getUUID(), new int[]{0,0,0}));

        
        try {
            UserManager.getInstance().updateUser(registeredUser);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
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

    private void addPlane(Plane plane) throws IOException {
        planes.put(plane.getUUID(), plane);
        Utilities.savePlanes(planes);
    }
    private void addHotel(Hotel hotel) throws IOException {
        hotels.put(hotel.getUUID(), hotel);
        Utilities.saveHotels(hotels); //Uncomment this when toJSON OBject is complete
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
