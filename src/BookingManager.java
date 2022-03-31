package src;

import java.io.FileNotFoundException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;
import java.util.UUID;

import org.json.simple.parser.ParseException;
/**
 * Booking Manager class manages all the bookings
 */

public class BookingManager {

    private static BookingManager bookingManager;

    private static HashMap<UUID,Plane> planes; //We will store all the planes in this hashmap. The uuid will be the key.
    private static HashMap<UUID,Hotel> hotels; //We will store all the hotels in this hashmap. The uuid will be the key.

    private static HashMap<UUID, Business> businesses;

    private static ArrayList<Plane> searchResults;
    private static ArrayList<Hotel> searchResultsHotel;

    /**
     * private constructor
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ParseException
     * @throws java.text.ParseException
     */
    private BookingManager() throws FileNotFoundException, IOException, ParseException, java.text.ParseException{

        planes = Utilities.loadPlanes(); //This should allow us to get the full list of planes from the JSON
        hotels = Utilities.loadHotels(); //This should allow us to get the full list of hotels from the JSON
        businesses = Utilities.loadBusinesses();


    }
    /**
    * gets the instance of booking manager
    * @return 
    * @throws FileNotFoundException
    * @throws IOException
    * @throws ParseException
    * @throws java.text.ParseException
    */
    public static BookingManager getInstance() throws FileNotFoundException, IOException, ParseException, java.text.ParseException {

        if (bookingManager == null)
            bookingManager = new BookingManager();

        return bookingManager;   

    }
    /**
     * This is used to search flights
     * @param departure is the city that plan is leaving from
     * @param destination is where the plan is landing
     */
    public void searchFlights(String departure, String destination) {
        System.out.println("Flight searches for: \""+departure+"\" to \"" + destination + "\"");
        searchResults = bookingManager.getFlights(departure, destination);
        int i = 1;
        for(Plane plane : searchResults){
            System.out.println(i + plane.getFlightInfo());
            i++;
        }
    }
    /**
     * This is used to search flights
     * @param search is the city that user is looking for
     */
    public void searchHotels(String search) {
        System.out.println("Flight searches for: \""+search+"\"");
        searchResultsHotel = bookingManager.getHotelBookings(search);
        int i = 1;
        for(Hotel hotel : searchResultsHotel){
            System.out.println(i + hotel.getHotelInfo());
            i++;
        }
    }
    /**
     * books flights
     * @param choice is the flight you choose
     * @return boolean it returns if booking the flight was successful
     */
    public boolean bookFlight(int choice) throws ParseException, java.text.ParseException, FileNotFoundException, IOException{

        if (choice >= searchResults.size()){
            System.out.println("Invalid Choice");
            return false;
        }
        System.out.println("Choose a seat in the format for you and each of your guests (A4, A5): ");


        searchResults.get(choice-1).printSeats();
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
            int number = Integer.parseInt(seat.substring(1,seat.length()))-1;
            if (number >= 20)
                return false; //input validation
            switch (seat.charAt(0)) {
                case 'A':
                    int[] index = {number, 0};
                    booking = new FlightBooking(searchResults.get(choice-1).getUUID(), index);
                    plane = searchResults.get(choice-1);
                    if (!plane.bookSeat(booking))
                        return false;
                    addPlane(plane);
                    registeredUser.addFlightBooking(booking);
                    break;
                case 'B':
                    int[] index2 = {number, 1};
                    booking = new FlightBooking(searchResults.get(choice-1).getUUID(), index2);
                    plane = searchResults.get(choice-1);
                    if (!plane.bookSeat(booking))
                        return false;
                    addPlane(plane);
                    registeredUser.addFlightBooking(booking);
                    break;
                case 'C':
                    int[] index3 = {number, 2};
                    booking = new FlightBooking(searchResults.get(choice-1).getUUID(), index3);
                    plane = searchResults.get(choice-1);
                    if (!plane.bookSeat(booking))
                        return false;
                    addPlane(plane);
                    registeredUser.addFlightBooking(booking);
                    break;
                case 'D':
                    int[] index4 = {number, 3};
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
    /**
     * books hotels 
     * @param choice is the hotel you choose
     * @return boolean it returns if booking the hotel was successful
     */
    public boolean bookHotel(int choice) throws ParseException, java.text.ParseException, FileNotFoundException, IOException{

        if (choice >= searchResultsHotel.size()){
            System.out.println("Invalid Choice");
            return false;
        }
        System.out.println("Enter the date in the format MM/dd/yyyy: ");
        Scanner input = new Scanner(System.in);
        String sDate = input.nextLine();

        Date date = Utilities.dobFormat.parse(sDate);

        


        RegisteredUser registeredUser = (RegisteredUser) UserManager.getInstance().getCurrentUser();

        Hotel hotel = searchResultsHotel.get(choice-1);

        if (hotel.bookRoom(new HotelBooking(searchResultsHotel.get(choice-1).getUUID(), new int[]{0,0,0},date))) {
            addHotel(hotel);

            registeredUser.addHotelBooking(new HotelBooking(searchResultsHotel.get(choice-1).getUUID(), new int[]{0,0,0},date));
            

            
            try {
                UserManager.getInstance().updateUser(registeredUser);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }
        return false;
      
    }
    /**
     * This constructor gets the planes
     * @return This returns the plane
     */
    public HashMap<UUID,Plane> getPlanes(){
        return this.planes;
    }
    /**
     * This constructor gets the hotels
     * @return This returns the hotels
     */
    public HashMap<UUID, Hotel> getHotels(){
        return this.hotels;
    }
    /**
     * This constructor gets the flights that match the given parameters 
     * @param departure gets the plane that has the specific departure date 
     * @param destination gets the plane that has the specific destination date 
     * @return This returns the results for the search for a flight
     */
    public ArrayList<Plane> getFlights(String departure, String destination) {

        departure = departure.toLowerCase();
        destination = destination.toLowerCase();

        ArrayList<Plane> results = new ArrayList<Plane>();
        if(departure == "" || departure == " " || destination == "" || destination == " ")
            return results;
        for (Plane plane : planes.values()) {//iterate through each archive in planes.
            if ((plane.getDestinationCity().toLowerCase().contains(destination) || destination.equalsIgnoreCase(plane.getDestinationCity())) && (plane.getDepartureCity().toLowerCase().contains(departure) || departure.equalsIgnoreCase(plane.getDepartureCity())) ){
                results.add(plane);
            }

        }
 
        return results;
    }
    /**
     * This constructor gets the flights that match the given parameters 
     * @param search gets the hotels within the city that is searched
     * @return This returns the results for the search for a a hotel
     */
    public ArrayList<Hotel> getHotelBookings(String search) {
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
    /**
     * This constructor saves a plan to a JSON file
     * @throws IOException
     */
    private void addPlane(Plane plane) throws IOException {
        planes.put(plane.getUUID(), plane);
        Utilities.savePlanes(planes);
    }
    /**
     * This constructor saves a hotel to a JSON file
     * @throws IOException
     */
    private void addHotel(Hotel hotel) throws IOException {
        hotels.put(hotel.getUUID(), hotel);
        Utilities.saveHotels(hotels); //Uncomment this when toJSON OBject is complete
    }
}
