package src;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.json.simple.JSONArray;

import java.util.ArrayList;


public class RegisteredUser extends User implements src.JSON{

    private ArrayList<FlightBooking> flightBookings = new ArrayList<>();
    private ArrayList<HotelBooking> hotelBookings = new ArrayList<>(); 
    private String[] preferences;

    public RegisteredUser(String username, String password, Date dob, Date creationDate) {
        super(username, password, dob, creationDate);
    }

    public RegisteredUser(String username, String password, Date dob, Date creationDate, ArrayList<FlightBooking> flightBookings, ArrayList<HotelBooking> hotelBookings, String[] preferences) {
        super(username, password, dob, creationDate);
        this.flightBookings = flightBookings;
        this.hotelBookings = hotelBookings;
        this.preferences = preferences;
    }

    public ArrayList<FlightBooking> getFlightBookings() {
        return this.flightBookings;
    }

    public ArrayList<HotelBooking> getHotelBookings() {
        return this.hotelBookings;
    }

    public void setPreferences() {
        Scanner input = new Scanner(System.in);

        if(preferences.length==0) {
            System.out.println("Preferred Airline: ");
            String airline = input.nextLine();
            System.out.println("Usual Trip Type:\n1. Round-Trip\n2. One-Way\n3. Any");
            String tripType = input.nextLine();
            System.out.println("Preferred Hotel: ");
            String hotel = input.nextLine();
            System.out.println("Flight Price Range (format: lowest price, highest price): ");
            String flightPrice = input.nextLine();
            System.out.println("Hotel Price Range (format: lowest price, highest price): ");
            String hotelPrice = input.nextLine();
            System.out.println("Preferred Coach: ");
            String coach = input.nextLine();
            System.out.println("Pets (y/n): ");
            String pets = input.nextLine();
            String[] temp = new String[] {airline, tripType, hotel, flightPrice, hotelPrice, coach, pets};
            preferences=temp;
        }
        else {
            System.out.println("Which one would you like to change?");
            int choice = input.nextLine();
            if(choice==1) {
                System.out.println("Preferred Airline: ");
                String airline = input.nextLine();
                preferences[0]=airline;
            }
            else if(choice==2) {
                System.out.println("Usual Trip Type:\n1. Round-Trip\n2. One-Way\n3. Any");
                String tripType = input.nextLine();
                preferences[1]=tripType;
            }
            else if(choice==3) {
                System.out.println("Preferred Hotel: ");
                String hotel = input.nextLine();
                preferences[2]=hotel;
            }
            else if(choice==4) {
                System.out.println("Flight Price Range (format: lowest price, highest price): ");
                String flightPrice = input.nextLine();
                preferences[3]=flightPrice;
            }
            else if(choice==5) {
                System.out.println("Hotel Price Range (format: lowest price, highest price): ");
                String hotelPrice = input.nextLine();
                preferences[4]=hotelPrice;
            }
            else if(choice==6) {
                System.out.println("Preferred Coach: ");
                String coach = input.nextLine();
                preferences[5]=coach;
            }
            else if(choice==7) {
                System.out.println("Pets (y/n): ");
                String pets = input.nextLine();
                preferences[6]=pets;
            }
            else {
                System.out.println("Invalid choice.");
            }
        }
    }

    public void addBooking() {
        //TODO
    }

    public void cancelBooking() {
        Scanner input = new Scanner(System.in);
        System.out.println("What kind of booking would you like to cancel?\n1. Flight\n2.Hotel");
        int option = input.nextLine();
        if(option==1) {
            for(int i=0;i<flightBookings.size();i++) {
                System.out.println((i+1)+". "+flightBookings.get(i));
            }
            System.out.println("Which booking would you like to cancel?\n");
            int answer = input.nextLine();
            flightBookings.remove(answer+1);
        }
        if(option==2) {
            for(int i=0;i<hotelBookings.size();i++) {
                System.out.println((i+1)+". "+hotelBookings.get(i));
            }
            System.out.println("Which booking would you like to cancel?\n");
            int answer = input.nextLine();
            hotelBookings.remove(answer+1);
        }

    }

    public String menuString() {

        return "\nWelcome, "+this.username+"!\n1. Set/Change Preferences\n2. Change Password\n3. View Past Bookings\n4. View Current Bookings\n5. Return to Main Menu\n\nWhat would you like to do?";
        
    }

    public void itinerary(ArrayList<FlightBooking> flightBookings, ArrayList<HotelBooking> hotelBookings) {
        try {
            FileWriter writer = new FileWriter("schedule.txt");
            writer.write(
                for(int i=0;i<flightBookings.size();i++) {
                    flightBookings.get(i);
                }
                for(int i=0;i<hotelBookings.size();i++) {
                    hotelBookings.get(i);
                }
            );
            writer.close();
            System.out.println("Your itinerary has been created!");
        } catch (IOException e) {
            System.out.println("error.");
            e.printStackTrace();
        }
    }

    
    public Map toJsonObject() {

        Map jsonObject = new LinkedHashMap<>();

        jsonObject.put("type","registered");
        jsonObject.put("username",this.username);
        jsonObject.put("creationDate", this.creationDate.toString());
        jsonObject.put("password", this.password);
        
        //String dobString = Utilities.dobFormat.format(this.dob);
        //System.out.println(dob);
        jsonObject.put("dob", Utilities.dobFormat.format(this.dob));

        JSONArray jsonFlight = new JSONArray();
        if (this.flightBookings != null)
            for (FlightBooking booking : this.flightBookings) {
                jsonFlight.add(booking.getUUID().toString());
                jsonFlight.add(String.valueOf(booking.getIndex()[0]));
                jsonFlight.add(String.valueOf(booking.getIndex()[1]));
            }
        jsonObject.put("flightBookings", jsonFlight);

        JSONArray jsonHotel = new JSONArray();
        if (this.hotelBookings != null)
            for (HotelBooking booking : this.hotelBookings) {
                jsonHotel.add(booking.getUUID().toString());
                jsonHotel.add(String.valueOf(booking.getIndex()[0]));
                jsonHotel.add(String.valueOf(booking.getIndex()[1]));
                jsonHotel.add(String.valueOf(booking.getIndex()[2]));
            }
        jsonObject.put("hotelBookings", jsonHotel);
    
        return jsonObject;
            
    }

    
    public String toString() {
        String ret = this.username + " " + this.password + " " + this.creationDate.toString() + " ";

        for (FlightBooking f : flightBookings)
            ret += f.toString() +" ";
        
        for (HotelBooking f : hotelBookings)
            ret += f.toString() + " ";

        return ret;
    }

}