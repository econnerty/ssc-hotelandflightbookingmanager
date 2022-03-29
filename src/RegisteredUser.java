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
        //TODO
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
            int choice = input.nextInt();
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

    public void addHotelBooking(HotelBooking booking) {
    	//TODO
    	hotelBookings.add(booking);
    }
    
    public void addFlightBooking(FlightBooking booking) {
        //TODO
    	flightBookings.add(booking);
    }

    public void cancelBooking() {
        //TODO
        Scanner input=new Scanner(System.in);
    	System.out.println("Which type of bookings would you like to cancel?\n"
    			+ "1. Flight\n2. Hotel");
    	int choice=input.nextInt();
    	if(choice==1) {
    		for(int i=0;i<flightBookings.size();i++) {
    			System.out.println((i+1)+". "+flightBookings.get(i));
    		}
    		System.out.println("Enter the number for the booking you want to cancel");
    		int answer=input.nextInt();
    		System.out.println(flightBookings.get(answer-1));
    		System.out.println("Is this the correct booking you want to cancel? y/n");
    		String pick=input.nextLine();
    		if(pick=="y") {
    			flightBookings.remove(answer-1);
    		}
    	}
    	else if(choice==2) {
    		for(int i=0;i<hotelBookings.size();i++) {
    			System.out.println(hotelBookings.get(i));
    		}
    		System.out.println("Enter the number for the booking you want to cancel");
    		int answer=input.nextInt();
    		System.out.println(hotelBookings.get(answer-1));
    		System.out.println("Is this the correct booking you want to cancel? y/n");
    		String pick=input.nextLine();
    		if(pick=="y") {
    			hotelBookings.remove(answer-1);
    		}
    	}
    	else {
    		System.out.println("Invalid answer");
    	}

    public String menuString() {

        return "\nWelcome, "+this.username+"!\n1. Set/Change Preferences\n2. Change Password\n3. View Past Bookings\n4. View Current Bookings\n5. Return to Main Menu\n\nWhat would you like to do?";
        
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