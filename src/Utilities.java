package src;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import java.security.*;
import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


/**
 * This class reads and writes JSON files
 * @author User
 *
 */
public class Utilities {

    private static final String USER_JSON_PATH = "data/users.json";
    private static final String PLANE_JSON_PATH = "data/planes.json";
    private static final String HOTEL_JSON_PATH = "data/hotels.json";
    private static final String BUSINESS_JSON_PATH = "data/businesses.json";

    private static final String DOB_FORMAT = "MM/dd/yyyy";
    //private static final String DATE_FORMAT = "MM/dd/yyyy' 'HH:mm:ss'Z'";
    private static final String DATE_FORMAT = "EEE MMM dd HH:mm:ss zzz yyyy";

    public static final Scanner input = new Scanner(System.in);


    private static MessageDigest md;
    private static JSONParser JSONParser;

    private static Random r = new Random();

    public static SimpleDateFormat dobFormat = new SimpleDateFormat(DOB_FORMAT);
    public static SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

    private static Utilities utilities;
    private static final String[] cities = new String[]{"San Francisco", "New York City", "Los Angeles", "Austin", 
    "Chicago", "Denver", "Portland", "Washington D.C.", "Atlanta", "Raleigh", "New Orleans",
    "San Diego", "Jacksonville", "Tallahasee", "Charlotte", "Charleston", "Columbia", "San Antonio", "Baltimore", "Kansas City",
    "Philadelphia", "Detroit", "Indianapolis", "San Jose", "Milwaukee", "Louisville", "Sacramento",
    "Memphis", "Oklahoma City", "Tucson", "El Paso", "Tulsa", "Fort Worth", "Mesa", "Arlington", "Bakersfield",
    "Houston", "Phoenix", "Dallas", "Salt Lake City", "Columbus", "Nashville", "Fresno", "Omaha", "Long Beach", "Virginia Beach",
    "Miami", "Oakland", "Minneapolis", "Wichita", "Tampa", "Aurora", "Honolulu", "Anaheim", "Lexington",
    "Stockton", "Corpus Christi", "Henderson", "Riverside", "Newark", "Saint Paul", "Santa Ana", "Cincinnati",
    "Irvine", "Boston", "Pittsburgh", "Orlando", "Greensboro", "Jersey City", "Anchorage", "Lincoln", "Plano", "Durham",
    "Buffalo", "Chandler", "Chula Vista", "Toledo"};
    
    private static final String[] international = new String[]{"Madis","Tokyo", "Paris", "London", "Bangkok", "Sydney", "Amsterdam",
    "Dubai", "Auckland", "Frankfurt", "Hong Kong", "Lahore", "Singapore", "Kuala Lumpur", "Delhi", "Antalya",
    "Istanbul", "Shenzhen", "Mumbai", "Rome", "Phuket", "Pattaya", "Taipei", "Mecca", "Guangzhou", "Prague", "Medina",
    "Seoul", "Amsterdam", "Agra", "Osaka", "Shanghai", "Ho Chi Minh City", "Denpasar", "Barcelona", "Milan", "Chenai",
    "Vienna", "Johor Bahru", "Jaipur", "Cancun", "Berlin", "Cairo", "Moscow", "Venice", "Madrid", "Ha Long", "Riyadh",
    "Dublin", "Florence", "Jerusalem", "Hanoi", "Toronto", "Johannesburg", "Sydney", "Munich", "Jakarta", "Beijing",
    "Saint Petersburgh", "Brussels", "Mugla", "Buenos Aires", "Chiba", "Frankfurt am Main", "Stockholm", "Lima", "Da Nang",
    "Batam", "Nice", "Fukuoka", "Abu Dhabi", "Jeju", "Porto", "Rhodes", "Rio de Janeiro","Krabi", "Bangalore", "Mexico City",
    "Punta Cana", "Sao Paulo", "Zurich", "Montreal", "Dusseldorf", "Chengdu", "Edinburgh", "Tehran", "Hamburg", "Cape Town",
    "Manila", "Bogota", "Xi'an", "Beirut", "Geneva", "Colombo", "Xiamen", "Bucharest", "Casablanca", "Sofia", "Dalian",
    "Montevideo","Budapest", "Lisbon", "Dammam", "Penang Island", "Heraklion", "Kyoto", "Zhuhai",
    "Vancouver", "Chiang Mai", "Copenhagen", "Melbourne", "Warsaw", "Marrakesh", "Kolkata", "Cebu City", "Lagos", "Liverpool", "Sheffield", "Manchester",
    "Leeds", "Leicester", "Bradford", "Coventry", "York" ,"Nottingham", "Oxford", "Southhampton", "Winchester", "Bath", "Norwich",
    "Portsmouth", "Preston", "Ely", "Gloucester", "Hereford", "Durham", "Truro", "Salford", "Lancaster", "Naples", "Bologna", "Verona",
    "Turin", "Palermo", "Pisa" ,"Ravenna", "Trieste", "Parma", "Rimini", "Padua", "Cagliri", "Pompeii", "Brescia", "Bergamo", "Modena",
    "Hat Yai", "Nonthaburi", "Thani", "Pak Kret", "Hua Hin", "Song", "Pai", "Pattan", "Nan", "Phrae", "Nong Khai", "Phayao"};
   
    private Utilities() {

        try {md = MessageDigest.getInstance("SHA-256");} 
        catch (NoSuchAlgorithmException e) {e.printStackTrace();}

        JSONParser = new JSONParser();
    }
    /**
     * This gets the instance of the Utilities
     * @return this return the utilities
     */
    public static Utilities getInstance() {
        
        if (utilities == null)
            Utilities.utilities = new Utilities();
        
        return Utilities.utilities;
        
    }
    /**
     * This loads the users that are in the hashmap
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ParseException
     * @throws java.text.ParseException
  */
    public static HashMap<String, User> loadUsers() throws FileNotFoundException, IOException, ParseException, java.text.ParseException{

        File file = new File(USER_JSON_PATH);

        if (!file.exists()) {
            file.createNewFile();
            FileWriter f = new FileWriter(USER_JSON_PATH);
            f.write("[]");
            f.flush();
            f.close();
            
        }


        JSONArray jsonArray = (JSONArray) JSONParser.parse(new FileReader(USER_JSON_PATH));
        HashMap<String, User> users = new HashMap<>();

        for (Object object : jsonArray) {

            JSONObject jsonObject = (JSONObject) object;

            String name = jsonObject.get("username").toString();
            String password = jsonObject.get("password").toString();
            Date dob = dobFormat.parse(jsonObject.get("dob").toString());
            Date creationDate = dateFormat.parse(jsonObject.get("creationDate").toString());
            String type = jsonObject.get("type").toString();          


            if (type.equalsIgnoreCase("registered")) {

                JSONArray jsonFlightBookings = (JSONArray) jsonObject.get("flightBookings");
                JSONArray jsonHotelBookings = (JSONArray) jsonObject.get("hotelBookings");
                JSONArray jsonPreferences = (JSONArray) jsonObject.get("preferences");
                JSONArray jsonGuests = (JSONArray) jsonObject.get("guests");
                
                ArrayList<FlightBooking> flightBookings = new ArrayList<>();
                ArrayList<HotelBooking> hotelBookings = new ArrayList<>();
                ArrayList<GuestUser> guests = new ArrayList<>();

                String[] preferences = new String[7];
    
                for (int i = 0; i < jsonPreferences.size(); i++) {
                    preferences[i] = jsonPreferences.get(i).toString();
                }
                

                for(int i = 0; i < jsonFlightBookings.size(); i+=3) { //the way the json is stored, we have to jump forward by 3 to get to the next uuid
                    int [] index = {Integer.parseInt(jsonFlightBookings.get(i+1).toString()), Integer.parseInt(jsonFlightBookings.get(i+2).toString())};
                    flightBookings.add(new FlightBooking(UUID.fromString(jsonFlightBookings.get(i).toString()), index));
                }

                for(int i = 0; i < jsonHotelBookings.size(); i+=5) { //the way the json is stored, we have to jump forward by 4 to get to the next uuid
                    int [] index = {Integer.parseInt(jsonHotelBookings.get(i+1).toString()), Integer.parseInt(jsonHotelBookings.get(i+2).toString()), Integer.parseInt(jsonHotelBookings.get(i+3).toString())};
                    Date date = dobFormat.parse(jsonHotelBookings.get(i+4).toString());
                    hotelBookings.add(new HotelBooking(UUID.fromString(jsonHotelBookings.get(i).toString()), index, date));
                }

                for (int i = 0; i < jsonGuests.size(); i++) {
                    guests.add(new GuestUser(jsonGuests.get(i).toString()));
                }


                if (preferences[0] != null)
                    users.put(name, new RegisteredUser(name, password, dob, creationDate, flightBookings, hotelBookings, preferences, guests));
                else
                    users.put(name, new RegisteredUser(name, password, dob, creationDate, flightBookings, hotelBookings, guests));


            }
            else if (type.equalsIgnoreCase("business")) {

                JSONArray jsonAirlines = (JSONArray) jsonObject.get("airlines");
                JSONArray jsonHotels = (JSONArray) jsonObject.get("hotels");
                Airlines[] airlines = new Airlines[jsonAirlines.size()];
                Hotels[] hotels = new Hotels[jsonHotels.size()];

                for(int i = 0; i < jsonAirlines.size(); i++) {
                    airlines[i] = Airlines.valueOf(jsonAirlines.get(i).toString());
                }

                for(int i = 0; i < jsonHotels.size(); i++) {
                    hotels[i] = Hotels.valueOf(jsonHotels.get(i).toString());
                }

                users.put(name, new BusinessUser(name, password, dob,creationDate, airlines, hotels));
            }

            
        }


        return users;
    }
    /**
     * This loads the planes that are contained in the hashmap
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ParseException
     * @throws java.text.ParseException
     */
    public static HashMap<UUID, Plane> loadPlanes() throws FileNotFoundException, IOException, ParseException, java.text.ParseException{


        File file = new File(PLANE_JSON_PATH);

        if (!file.exists()) {
            file.createNewFile();

            FileWriter f = new FileWriter(PLANE_JSON_PATH);
            f.write("[]");
            f.flush();
            f.close();
            
        }

        JSONArray jsonArray = (JSONArray) JSONParser.parse(new FileReader(PLANE_JSON_PATH));
        HashMap<UUID, Plane> planes = new HashMap<>();

        for (Object object : jsonArray) {

            JSONObject jsonObject = (JSONObject) object;


            UUID uuid = UUID.fromString(jsonObject.get("uuid").toString());
            Airlines airline = Airlines.valueOf(jsonObject.get("airline").toString());
            int availableSeats=Integer.parseInt(jsonObject.get("availableSeats").toString());
            boolean[][] seats = new boolean[Plane.getSize()[0]][Plane.getSize()[1]];
            String destinationCity = jsonObject.get("destinationCity").toString();
            String departureCity = jsonObject.get("departureCity").toString();
            Date departureDate = dateFormat.parse(jsonObject.get("departureDate").toString());
            Date arrivalDate = dateFormat.parse(jsonObject.get("arrivalDate").toString());
            Double price = Double.parseDouble(jsonObject.get("price").toString());
            boolean smoking = Boolean.parseBoolean(jsonObject.get("smoking").toString());
            boolean petsAllowed = Boolean.parseBoolean(jsonObject.get("petsAllowed").toString());
            JSONArray layovers = (JSONArray) jsonObject.get("layovers");
            String[] sLayovers = new String[layovers.size()];

            

            JSONArray jsonSeats = (JSONArray) jsonObject.get("seats");

        
            for (int p = 0; p < jsonSeats.size(); p++) {
                JSONArray jsonSeats2 = (JSONArray) jsonSeats.get(p);
                for (int j = 0; j < jsonSeats2.size(); j++){
                    seats[p][j] = Integer.parseInt(jsonSeats2.get(j).toString()) != 0;
                }
            }
            
            int i = 0;
            for (Object object2 : layovers) {
                sLayovers[i] = object2.toString();
                i++;
            }


            planes.put(uuid, new Plane(uuid, airline, availableSeats, price, departureDate, arrivalDate, departureCity, destinationCity, smoking, petsAllowed, seats,sLayovers));
            //System.out.println(planes.get(uuid).toString());

        }
        
 
        return planes;
    }
    /**
     * This loads the hotels that are contained in the hashmap 
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ParseException
     * @throws java.text.ParseException
     */
    public static HashMap<UUID, Hotel> loadHotels() throws FileNotFoundException, IOException, ParseException, java.text.ParseException{

        File file = new File(HOTEL_JSON_PATH);

        if (!file.exists()) {
            file.createNewFile();
            FileWriter f = new FileWriter(HOTEL_JSON_PATH);
            f.write("[]");
            f.flush();
            f.close();
        }
        
        JSONArray jsonArray = (JSONArray) JSONParser.parse(new FileReader(HOTEL_JSON_PATH));
        HashMap<UUID, Hotel> hotels = new HashMap<>();

        for (Object object : jsonArray) {

            JSONObject jsonObject = (JSONObject) object;

            UUID uuid = UUID.fromString(jsonObject.get("uuid").toString());
            Hotels hotel = Hotels.valueOf(jsonObject.get("hotel").toString());
            int availableRooms = Integer.parseInt(jsonObject.get("availableRooms").toString());
            boolean[][][] rooms = new boolean[Hotel.getSize()[0]][Hotel.getSize()[1]][Hotel.getSize()[2]];
            String city = jsonObject.get("city").toString();
            double price = Double.parseDouble(jsonObject.get("price").toString());
            boolean smoking = Boolean.parseBoolean(jsonObject.get("smoking").toString());
            boolean petsAllowed = Boolean.parseBoolean(jsonObject.get("petsAllowed").toString());
            double rating = Double.parseDouble(jsonObject.get("rating").toString());

            ArrayList<Amenities> amenities = new ArrayList<>();

            JSONArray jsonAmenities = (JSONArray) jsonObject.get("amenities");

            for (Object obj : jsonAmenities) {
                amenities.add(Amenities.valueOf(obj.toString()));
            }

            hotels.put(uuid, new Hotel(uuid, availableRooms, hotel, price, city, smoking, petsAllowed, rooms,rating,amenities));
        }
        return hotels;
    }
    /**
     * This loads the Businesses that are contained in the hashmap
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ParseException
     * @throws java.text.ParseException
     */
    public static HashMap<UUID, Business> loadBusinesses() throws FileNotFoundException, IOException, ParseException, java.text.ParseException {
        File file = new File(BUSINESS_JSON_PATH);

        if (!file.exists()) {
            file.createNewFile();

            FileWriter f = new FileWriter(BUSINESS_JSON_PATH);
            f.write("[]");
            f.flush();
            f.close();
        }
        JSONArray jsonArray = (JSONArray) JSONParser.parse(new FileReader(BUSINESS_JSON_PATH));
        HashMap<UUID, Business> businesses = new HashMap<>();

        for (Object object : jsonArray) {

            JSONObject jsonObject = (JSONObject) object;

            UUID uuid = UUID.fromString(jsonObject.get("UUID").toString());
            String name = jsonObject.get("name").toString();
            Double rating = Double.parseDouble(jsonObject.get("rating").toString());
            int numRatings=Integer.parseInt(jsonObject.get("numRatings").toString());

            businesses.put(uuid, new Business(uuid, name, rating, numRatings));
            //System.out.println(planes.get(uuid).toString());
        }
        return businesses;
    }
    /** 
     * This is saving the users to the JSON file
     * @param users these are the users that were created
     * @throws IOException
     */
    public static void saveUsers(HashMap<String, User> users) throws IOException {

        JSONArray jsonUsers = new JSONArray();

        for (User user : users.values()) {
            jsonUsers.add(user.toJsonObject());
        }

        FileWriter f = new FileWriter(USER_JSON_PATH);
        f.write(jsonUsers.toJSONString().replace("},{", "},\n{"));
        f.flush();

    }
    /** 
     * This is saving the flights to the JSON file
     * @param planes these are the flights that were created by the user
     * @throws IOException
     */
    public static void savePlanes(HashMap<UUID, Plane> planes) throws IOException{
        JSONArray jsonPlanes = new JSONArray();

        for (Plane plane : planes.values()) {
            jsonPlanes.add(plane.toJsonObject());
        }

        FileWriter f = new FileWriter(PLANE_JSON_PATH);
        f.write(jsonPlanes.toJSONString().replace("},{", "},\n{"));
        f.flush();
    }
    /** 
     * This is saving the hotels to the JSON file
     * @param hotels these are the hotels that were created by the user
     * @throws IOException
     */
    public static void saveHotels(HashMap<UUID, Hotel> hotels) throws IOException{
        JSONArray jsonHotels = new JSONArray();

        for (Hotel hotel : hotels.values()) {
            jsonHotels.add(hotel.toJsonObject());
        }

        FileWriter f = new FileWriter(HOTEL_JSON_PATH);
        f.write(jsonHotels.toJSONString().replace("},{", "},\n{"));
        f.flush();
    }

    //TODO Make sure flight has a different destination than its departure city
    /**
     * This generates random flights in the JSON file
     * @throws FileNotFoundException
     * @throws IOException
     * @throws java.text.ParseException
     * @throws NoSuchFieldException
     * @throws SecurityException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public static void generateFlights() throws FileNotFoundException, IOException, java.text.ParseException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
        JSONArray jsonFlights = new JSONArray();

        Date between1 = new Date();
        Date between2 = dobFormat.parse("07/31/2022");

        for (int i = 0; i < 500; i++) { //want to create 500 flight

            Map jsonObject = new LinkedHashMap<>();

            Double price = Double.parseDouble(new DecimalFormat("#.##").format(ThreadLocalRandom.current().nextDouble(100.00,650.00)));
   

            Date departureDate = new Date(ThreadLocalRandom.current().nextLong(between1.getTime(), between2.getTime()));
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(departureDate);
            calendar.add(Calendar.HOUR_OF_DAY, r.nextInt(4)+3);

            JSONArray layovers = new JSONArray();

            int layoverCount = r.nextInt(4);

            for (int j = 0; j < layoverCount; j++) {
                layovers.add(cities[r.nextInt(cities.length)]);
            }

            Date arrivalDate = calendar.getTime();

            int[][] seats = new int[Plane.getSize()[0]][Plane.getSize()[1]];

            for (int[] seat : seats) 
                Arrays.fill(seat, 0);


            jsonObject.put("uuid", UUID.randomUUID().toString());
            jsonObject.put("airline", Airlines.values()[r.nextInt(10)].toString()); 
            jsonObject.put("availableSeats", Plane.getSize()[0]*Plane.getSize()[1]);

            int randomCity1 = r.nextInt(Utilities.cities.length);
            int randomCity2 = r.nextInt(Utilities.cities.length);

            while (randomCity1 == randomCity2)
                randomCity2 = r.nextInt(Utilities.cities.length);

            jsonObject.put("departureCity", Utilities.cities[randomCity1]);
            jsonObject.put("destinationCity",Utilities.cities[randomCity2]);
            jsonObject.put("departureDate", departureDate.toString());
            jsonObject.put("arrivalDate", arrivalDate.toString());
            jsonObject.put("layovers", layovers);
            jsonObject.put("price", price);
            jsonObject.put("smoking", (r.nextInt(10) < 1) ? true : false);
            jsonObject.put("petsAllowed", (r.nextInt(5) < 1) ? true : false);

            JSONArray jsonSeats = new JSONArray();
            for (int p = 0; p < seats.length; p++) {
                JSONArray arr = new JSONArray();
                for (int j = 0; j < seats[p].length; j++) {
                    arr.add(seats[p][j]);
                }
                jsonSeats.add(arr);
            }
            jsonObject.put("seats", jsonSeats);
            

            jsonFlights.add(jsonObject);

        }

        FileWriter f = new FileWriter(PLANE_JSON_PATH);
        f.write(jsonFlights.toJSONString().replace("},{", "},\n{"));
        f.flush();
    }
}
