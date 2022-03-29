package src;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
<<<<<<< HEAD
import java.io.FileWriter;
import java.io.IOException;
<<<<<<< HEAD
import java.util.Scanner;
=======
=======
>>>>>>> origin/Shaine
>>>>>>> 9f6fe73ef85f4ac88f5a25ac07b2a6e6b64d329a

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