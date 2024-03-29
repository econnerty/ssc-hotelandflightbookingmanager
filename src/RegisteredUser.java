package src;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Scanner;


import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;

/**
 * This class create user that is registered
 */
public class RegisteredUser extends User implements src.JSON{

    private ArrayList<FlightBooking> flightBookings = new ArrayList<>();
    private ArrayList<HotelBooking> hotelBookings = new ArrayList<>(); 
    private String[] preferences; 
    private ArrayList<GuestUser> guests = new ArrayList<>();
    /**
     * This is constructing the Registered user
     * @param username this the username of the user
     * @param password this the password of the user
     * @param dob date of birth of the user
     * @param creationDate date of creation of account
     */
    public RegisteredUser(String username, String password, Date dob, Date creationDate) {
        super(username, password, dob, creationDate);
    }
    /**
     * This is constructing the Registered user
     * @param username this the username of the user
     * @param password this the password of the user
     * @param dob date of birth of the user
     * @param creationDate date of creation of account
     * @param flightbookings is the flights the user has booked
     * @param hotelbooking is the hotels the user has booked
     * @param guests is amount of guest the user is booking for
     */
    public RegisteredUser(String username, String password, Date dob, Date creationDate, ArrayList<FlightBooking> flightBookings, ArrayList<HotelBooking> hotelBookings, ArrayList<GuestUser> guests) {
        super(username, password, dob, creationDate);
        this.flightBookings = flightBookings;
        this.hotelBookings = hotelBookings;
        this.guests = guests;
    }
    /**
     * This is constructing the Registered user
     * @param username this the username of the user
     * @param password this the password of the user
     * @param dob date of birth of the user
     * @param creationDate date of creation of account
     * @param flightbookings is the flights the user has booked
     * @param hotelbooking is the hotels the user has booked
     * @param preferences this the preferences that the user wants for flights and hotels
     * @param guests is amount of guest the user is booking for
     */
    public RegisteredUser(String username, String password, Date dob, Date creationDate, ArrayList<FlightBooking> flightBookings, ArrayList<HotelBooking> hotelBookings, String[] preferences, ArrayList<GuestUser> guests) {
        super(username, password, dob, creationDate);
        this.flightBookings = flightBookings;
        this.hotelBookings = hotelBookings;
        this.preferences = preferences;
        this.guests = guests;
    }
    /**
     * This is the flights that the user has
     * @return this return the flights of the user
     */
    public ArrayList<FlightBooking> getFlightBookings() {
        return this.flightBookings;
    }
    /**
     * This is the flights that the user has
     * @return this return the flights of the user
     */
    public ArrayList<HotelBooking> getHotelBookings() {
        return this.hotelBookings;
    }
    /**
     * This prints out the itinerary
     * @throws ParseException
     * @throwsjava.text.ParseException
     */
    public void itinerary() throws ParseException, java.text.ParseException {
        String write = "";
    	try {
    	      FileWriter myWriter = new FileWriter("itinerary.txt");
              write += "Itinerary for " + this.username + ": \n\n";
    	      for(FlightBooking flightBooking: flightBookings) {
                write += "\tFlight Booking:\n";
                write += "\tYour departure date is: "+BookingManager.getInstance().getPlanes().get(flightBooking.getUUID()).getDepartureDate().toString()+"\n";
                write += "\tYour arrival date is: "+BookingManager.getInstance().getPlanes().get(flightBooking.getUUID()).getArrivalDate().toString()+" at "+BookingManager.getInstance().getPlanes().get(flightBooking.getUUID()).getDepartureCity()+"\n";
                write +="\tand will be arriving at "+BookingManager.getInstance().getPlanes().get(flightBooking.getUUID()).getDestinationCity();
    		  }
    	      for(HotelBooking hotelBooking : hotelBookings) {
                write += "\tHotel Booking: \n";
                write += "\tYou have booked a hotel at " + BookingManager.getInstance().getHotels().get(hotelBooking.getUUID()).getName() + " in "+ BookingManager.getInstance().getHotels().get(hotelBooking.getUUID()).getCity() + "\n" ;
                write +="\tYour booking date is: "+Utilities.dobFormat.format(hotelBooking.getDate())+"\n";
    		  }
              myWriter.write(write);
    	      myWriter.close();
    	      System.out.println("Your itinerary has been created!");
    	    } catch (IOException e) {
                System.out.println("Something went wrong printing your itinerary!");
    	      e.printStackTrace();
    	    }
        
  
    }
    /**
     * This sets the preferences of the registered user
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ParseException
     * @throws java.text.ParseException
     */
    public void setPreferences() throws FileNotFoundException, IOException, ParseException, java.text.ParseException {
        //TODO
        Scanner input = new Scanner(System.in);

        if(preferences == null) {
            this.preferences = new String[7];
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
            int choice = Integer.parseInt(input.nextLine());
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
        UserManager.getInstance().updateUser(this);
    }
    /**
     * This allows the user to add friends or guests 
     * @param name this the name of the friends that user is adding
     */
    public void addFriends(String name) {
        if (this.guests == null)
            this.guests = new ArrayList<GuestUser>();
        guests.add(new GuestUser(name));
        try {
            UserManager.getInstance().updateUser(this);
        } catch (IOException | ParseException | java.text.ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    /**
     * This allows the user to remove friends or guests 
     * @param name this the name of the friends that user is removing
     */
    public void removeFriend(String name) {
        if (this.guests == null)
            this.guests = new ArrayList<GuestUser>();
        guests.remove(new GuestUser(name));
        try {
            UserManager.getInstance().updateUser(this);
        } catch (IOException | ParseException | java.text.ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    /**
     * This is the list of friends or guests that the user has added
     * @return this return the guests
     */
    public ArrayList<GuestUser> getFriends() {
        return this.guests;
    }
    /**
     * This allows the user to change their password
     */
    public void changePassword() {
        System.out.println("Enter your new password: ");
        Scanner input = new Scanner(System.in);
        this.password = input.nextLine();
        try {
            UserManager.getInstance().updateUser(this);
        } catch (IOException | ParseException | java.text.ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * This allows the user to add a hotel to their booking
     * @param booking this is the hotel that is being booked
     */
    public void addHotelBooking(HotelBooking booking) {
    	//TODO
    	hotelBookings.add(booking);
    }
    /**
     * This allows the user to add a flight to their booking
     * @param booking this is the flight that is being booked
     */
    public void addFlightBooking(FlightBooking booking) {
        //TODO
    	flightBookings.add(booking);
    }
    /**
     * This allows the user to cancel an unwanted booking
     */
    public void cancelBooking() {
        //TODO
        Scanner input=new Scanner(System.in);
    	System.out.println("Which type of bookings would you like to cancel?\n"
    			+ "1. Flight\n2. Hotel");
    	int choice = Integer.parseInt(input.nextLine());
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
    /**
     * This contains the string for the menu
     * @return this return the string for the menu
     */
    public String menuString() {

        return "\nWelcome, "+this.username+"!\n1. Set/Change Preferences\n2. Change Password\n3. View Past Bookings\n4. View Current Bookings\n5. Search/Book a Flight\n6. Search/Book a Hotel\n7. Add a friend\n8. Remove a friend\n9. Print Itinerary\n10. Log Out\n\nWhat would you like to do?";
        
    }

    /**
     * This return JSON object which can be written to a JSON file
     * @return This returns the JSON object 
     */
    public Map toJsonObject() {

        Map jsonObject = new LinkedHashMap<>();

        jsonObject.put("type","registered");
        jsonObject.put("username",this.username);
        jsonObject.put("creationDate", this.creationDate.toString());
        jsonObject.put("password", this.password);
        
        //String dobString = Utilities.dobFormat.format(this.dob);
        //System.out.println(dob);
        jsonObject.put("dob", Utilities.dobFormat.format(this.dob));

        jsonObject.put("preferences",preferences);
        JSONArray jsonPreferences = new JSONArray();
        if (this.preferences != null)
            for (String pref : this.preferences) {
                jsonPreferences.add(pref);
            }
        jsonObject.put("preferences", jsonPreferences);

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
                jsonHotel.add(Utilities.dobFormat.format(booking.getDate()));
            }
        jsonObject.put("hotelBookings", jsonHotel);

        JSONArray jsonGuests = new JSONArray();
        if (this.guests != null)
            for (GuestUser guest : this.guests) {
                jsonGuests.add(guest.username);
            }
        jsonObject.put("guests", jsonGuests);
    
        return jsonObject;
            
    }

    /**
     * Returns the string for Registered user
     * @return this return the string
     */
    public String toString() {
        String ret = this.username + " " + this.password + " " + this.creationDate.toString() + " ";

        for (FlightBooking f : flightBookings)
            ret += f.toString() +" ";
        
        for (HotelBooking f : hotelBookings)
            ret += f.toString() + " ";

        return ret;
    }

}