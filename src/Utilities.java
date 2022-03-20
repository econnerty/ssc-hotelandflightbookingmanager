package src;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.RowFilter.ComparisonType;

import java.security.*;
import java.io.*;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



public class Utilities {

    private static final String USER_JSON_PATH = "data/users.json";
    private static final String PLANE_JSON_PATH = "data/planes.json";
    private static final String HOTEL_JSON_PATH = "data/hotels.json";

    private static final String DOB_FORMAT = "MM/dd/yyyy";
    //private static final String DATE_FORMAT = "MM/dd/yyyy' 'HH:mm:ss'Z'";
    private static final String DATE_FORMAT = "EEE MMM dd HH:mm:ss zzz yyyy";

    private static SimpleDateFormat dobFormat = new SimpleDateFormat(DOB_FORMAT);
    private static SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

    private static MessageDigest md;
    private static JSONParser JSONParser;

    private static Random r = new Random();

    private static Utilities utilities;
    private static final String[] cities = new String[]{"San Francisco", "New York City", "Los Angeles", "Austin", 
    "Chicago", "Denver", "Portland", "Seattle", "Washington D.C.", "Atlanta", "Raleigh", "New Orleans",
    "San Diego", "Jacksonville", "Tallahasee", "Charlotte", "Columbia", "San Antonio", "Baltimore", "Kansas City",
    "Philadelphia", "Detroit", "Indianapolis", "San Jose", "Milwaukee", "Louisville", "Sacramento",
    "Memphis", "Oklahoma City", "Tucson", "El Paso", "Tulsa", "Fort Worth", "Mesa", "Arlington", "Bakersfield",
    "Houston", "Phoenix", "Dallas", "Columbus", "Nashville", "Fresno", "Omaha", "Long Beach", "Virginia Beach",
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
    "Batam", "Nice", "Fukuoka", "Abu Dhabi", "Jeju", "Porto", "Rhodes", "Rio de Janeiro", /*what this*/"Krabi"/*the fuck is this*//*um hello*//*bitch*/, "Bangalore", "Mexico City",
    "Punta Cana", "Sao Paulo", "Zurich", "Montreal", "Dusseldorf", "Chengdu", "Edinburgh", "Tehran", "Hamburg", "Cape Town",
    "Manila", "Bogota", "Xi'an", "Beirut", "Geneva", "Colombo", "Xiamen", "Bucharest", "Casablanca", "Sofia", "Dalian",
    "Montevideo", "","Budapest", "Lisbon", "Dammam", "Penang Island", "Heraklion", "Kyoto", "Zhuhai",
    "Vancouver", "Chiang Mai", "Copenhagen", "Melbourne", "Warsaw", "Marrakesh", "Kolkata", "Cebu City", "Lagos", "Liverpool", "Sheffield", "Manchester",
    "Leeds", "Leicester", "Bradford", "Coventry", "York" ,"Nottingham", "Oxford", "Southhampton", "Winchester", "Bath", "Norwich",
    "Portsmouth", "Preston", "Ely", "Gloucester", "Hereford", "Durham", "Truro", "Salford", "Lancaster", "Naples", "Bologna", "Verona",
    "Turin", "Palermo", "Pisa" ,"Ravenna", "Trieste", "Parma", "Rimini", "Padua", "Cagliri", "Pompeii", "Brescia", "Bergamo", "Modena",
    "Hat Yai", "Nonthaburi", "Thani", "Pak Kret", "Hua Hin", "Song", "Pai", "Pattan", "Nan", "Phrae", "Nong Khai", "Phayao"};
    //bish u can stop now/ /bish//uwu
    //bish we need to just write the code for the random stufff, also we need to randomize the airlines and hotel which we only have 2  of those rn
    //hmmm can u separate the internationalones uwu .....//girl pls//girllistenn t
    //
//boston//chorleston//atlanta//london//bangkok bitch r we doing international//tokyo//paris//russia//beijing//bitch//shesaidtodointernational bitch fuck u said russia HAHAAH//nowcalculatethedistancetoeachcitysowecandoit
    //hmmm can u separate the internationalones uwu .....//girl pls//girllistenn t
    //
//boston//chorleston//atlanta//london//bangkok bitch r we doing international//tokyo//paris//russia//beijing//bitch//shesaidtodointernational bitch fuck u said russia HAHAAH//nowcalculatethedistancetoeachcitysowecandoit
    private Utilities() {

        try {md = MessageDigest.getInstance("SHA-256");} 
        catch (NoSuchAlgorithmException e) {e.printStackTrace();}

        JSONParser = new JSONParser();
    }

    public static Utilities getInstance() {
        
        if (utilities == null)
            Utilities.utilities = new Utilities();
        
        return Utilities.utilities;
        
    }

    public static HashMap<String, Users> loadUsers() throws FileNotFoundException, IOException, ParseException, java.text.ParseException{

        File file = new File(USER_JSON_PATH);

        if (!file.exists()) {
            file.createNewFile();
            FileWriter f = new FileWriter(USER_JSON_PATH);
            f.write("[]");
            f.flush();
            f.close();
            
        }


        JSONArray jsonArray = (JSONArray) JSONParser.parse(new FileReader(USER_JSON_PATH));
        HashMap<String, Users> users = new HashMap<>();

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
                
                ArrayList<FlightBooking> flightBookings = new ArrayList<>();
                ArrayList<HotelBooking> hotelBookings = new ArrayList<>();

                String[] preferences = new String[5]; //TODO Parse preferences

                for(int i = 0; i < jsonFlightBookings.size(); i+=3) { //the way the json is stored, we have to jump forward by 3 to get to the next uuid
                    int [] index = {Integer.parseInt(jsonFlightBookings.get(i+1).toString()), Integer.parseInt(jsonFlightBookings.get(i+2).toString())};
                    flightBookings.add(new FlightBooking(UUID.fromString(jsonFlightBookings.get(i).toString()), index));
                }

                for(int i = 0; i < jsonHotelBookings.size(); i+=4) { //the way the json is stored, we have to jump forward by 4 to get to the next uuid
                    int [] index = {Integer.parseInt(jsonHotelBookings.get(i+1).toString()), Integer.parseInt(jsonHotelBookings.get(i+2).toString()), Integer.parseInt(jsonHotelBookings.get(i+3).toString())};
                    hotelBookings.add(new HotelBooking(UUID.fromString(jsonHotelBookings.get(i).toString()), index));
                }

                users.put(name, new RegisteredUser(name, password, dob, creationDate, flightBookings, hotelBookings, preferences));


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
            System.out.println(uuid.toString());
            Airlines airline = Airlines.valueOf(jsonObject.get("airline").toString());
            int availableSeats=Integer.parseInt(jsonObject.get("availableSeats").toString());
            boolean[][] seats = new boolean[Plane.getSize()[0]][Plane.getSize()[1]];
            String destinationCity = jsonObject.get("destinationCity").toString();
            String departureCity = jsonObject.get("departureCity").toString();
            Date departureDate = dateFormat.parse(jsonObject.get("departureDate").toString()); //this is supposed to be a date tho i hate u :pensive:
            //i am sad why it red
            Date arrivalDate = dateFormat.parse(jsonObject.get("arrivalDate").toString());
            Double price = Double.parseDouble(jsonObject.get("price").toString());
            boolean smoking = Boolean.parseBoolean(jsonObject.get("smoking").toString());
//stop follow me :pensive: girl
            boolean petsAllowed = Boolean.parseBoolean(jsonObject.get("petsAllowed").toString());

            planes.put(uuid, new Plane(uuid, airline, availableSeats, price, departureDate, arrivalDate, departureCity, destinationCity, smoking, petsAllowed, seats));
            System.out.println(planes.get(uuid).toString());

        }
        
 
        return planes;
    }

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

            hotels.put(uuid, new Hotel(uuid, availableRooms, hotel, price, city, smoking, petsAllowed, rooms));

        }

        return hotels;
    } 

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

            Date arrivalDate = calendar.getTime();

            boolean seats[][] = new boolean[Plane.getSize()[0]][Plane.getSize()[1]];

            for (boolean[] seat : seats) 
                Arrays.fill(seat, false);


            jsonObject.put("uuid", UUID.randomUUID().toString());
            jsonObject.put("airline", Airlines.values()[r.nextInt(Airlines.values().length)].toString());
            jsonObject.put("availableSeats", Plane.getSize()[0]*Plane.getSize()[1]);
            jsonObject.put("departureCity", Utilities.cities[r.nextInt(Utilities.cities.length)]);
            jsonObject.put("destinationCity",Utilities.cities[r.nextInt(Utilities.cities.length)]);
            jsonObject.put("departureDate", departureDate.toString());
            jsonObject.put("arrivalDate", arrivalDate.toString());
            jsonObject.put("price", price);
            jsonObject.put("smoking", (r.nextInt(1) < 10 ? true : false));
            jsonObject.put("petsAllowed", (r.nextInt(1) < 5 ? true : false));
            jsonObject.put("seats", Arrays.deepToString(seats));

            jsonFlights.add(jsonObject);

        }

        for (int i = 0; i < 100; i++) { //want to create 500 flight

            Map jsonObject = new LinkedHashMap<>();


            Date departureDate = new Date(ThreadLocalRandom.current().nextLong(between1.getTime(), between2.getTime()));
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(departureDate);
            calendar.add(Calendar.HOUR_OF_DAY, r.nextInt(12)+12);

            Double price = Double.parseDouble(new DecimalFormat("#.##").format(ThreadLocalRandom.current().nextDouble(800.00,1650.00)));

            Date arrivalDate = calendar.getTime();

            boolean seats[][] = new boolean[Plane.getSize()[0]][Plane.getSize()[1]];

            for (boolean[] seat : seats) 
                Arrays.fill(seat, false);


            jsonObject.put("uuid", UUID.randomUUID().toString());
            jsonObject.put("airline", Airlines.values()[r.nextInt(Airlines.values().length)].toString());
            jsonObject.put("availableSeats", Plane.getSize()[0]*Plane.getSize()[1]);
            jsonObject.put("departureCity", "New York City");
            jsonObject.put("destinationCity",Utilities.international[r.nextInt(Utilities.international.length)]);
            jsonObject.put("departureDate", departureDate.toString());
            jsonObject.put("arrivalDate", arrivalDate.toString());
            jsonObject.put("price", price);
            jsonObject.put("smoking", (r.nextInt(1) < 10 ? true : false));
            jsonObject.put("petsAllowed", (r.nextInt(1) < 5 ? true : false));
            jsonObject.put("seats", Arrays.deepToString(seats));

            jsonFlights.add(jsonObject);

        }

        for (int i = 0; i < 100; i++) { //want to create 500 flight

            Map jsonObject = new LinkedHashMap<>();

            Date departureDate = new Date(ThreadLocalRandom.current().nextLong(between1.getTime(), between2.getTime()));
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(departureDate);
            calendar.add(Calendar.HOUR_OF_DAY, r.nextInt(12)+12);

            Double price = Double.parseDouble(new DecimalFormat("#.##").format(ThreadLocalRandom.current().nextDouble(800.00,1650.00)));

            Date arrivalDate = calendar.getTime();

            boolean seats[][] = new boolean[Plane.getSize()[0]][Plane.getSize()[1]];

            for (boolean[] seat : seats) 
                Arrays.fill(seat, false);


            jsonObject.put("uuid", UUID.randomUUID().toString());
            jsonObject.put("airline", Airlines.values()[r.nextInt(Airlines.values().length)].toString());
            jsonObject.put("availableSeats", Plane.getSize()[0]*Plane.getSize()[1]);
            jsonObject.put("departureCity", "Los Angeles");
            jsonObject.put("destinationCity",Utilities.international[r.nextInt(Utilities.international.length)]);
            jsonObject.put("departureDate", departureDate.toString());
            jsonObject.put("arrivalDate", arrivalDate.toString());
            jsonObject.put("price", price);
            jsonObject.put("smoking", (r.nextInt(1) < 10 ? true : false));
            jsonObject.put("petsAllowed", (r.nextInt(1) < 5 ? true : false));
            jsonObject.put("seats", Arrays.deepToString(seats));

            jsonFlights.add(jsonObject);

        }
        for (int i = 0; i < 100; i++) { //want to create 500 flight

            Map jsonObject = new LinkedHashMap<>();

            Date departureDate = new Date(ThreadLocalRandom.current().nextLong(between1.getTime(), between2.getTime()));
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(departureDate);
            calendar.add(Calendar.HOUR_OF_DAY, r.nextInt(12)+12);

            Date arrivalDate = calendar.getTime();

            Double price = Double.parseDouble(new DecimalFormat("#.##").format(ThreadLocalRandom.current().nextDouble(800.00,1650.00)));

            boolean seats[][] = new boolean[Plane.getSize()[0]][Plane.getSize()[1]];

            for (boolean[] seat : seats) 
                Arrays.fill(seat, false);


            jsonObject.put("uuid", UUID.randomUUID().toString());
            jsonObject.put("airline", Airlines.values()[r.nextInt(Airlines.values().length)].toString());
            jsonObject.put("availableSeats", Plane.getSize()[0]*Plane.getSize()[1]);
            jsonObject.put("departureCity", "Atlanta");
            jsonObject.put("destinationCity",Utilities.international[r.nextInt(Utilities.international.length)]);
            jsonObject.put("departureDate", departureDate.toString());
            jsonObject.put("arrivalDate", arrivalDate.toString());
            jsonObject.put("price", price);
            jsonObject.put("smoking", (r.nextInt(1) < 10 ? true : false));
            jsonObject.put("petsAllowed", (r.nextInt(1) < 5 ? true : false));
            jsonObject.put("seats", Arrays.deepToString(seats));

            jsonFlights.add(jsonObject);

        }

        for (int i = 0; i < 100; i++) { //want to create 500 flight

            Map jsonObject = new LinkedHashMap<>();

            Date departureDate = new Date(ThreadLocalRandom.current().nextLong(between1.getTime(), between2.getTime()));
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(departureDate);
            calendar.add(Calendar.HOUR_OF_DAY, r.nextInt(12)+12);

            Date arrivalDate = calendar.getTime();

            Double price = Double.parseDouble(new DecimalFormat("#.##").format(ThreadLocalRandom.current().nextDouble(800.00,1650.00)));

            boolean seats[][] = new boolean[Plane.getSize()[0]][Plane.getSize()[1]];

            for (boolean[] seat : seats) 
                Arrays.fill(seat, false);


            jsonObject.put("uuid", UUID.randomUUID().toString());
            jsonObject.put("airline", Airlines.values()[r.nextInt(Airlines.values().length)].toString());
            jsonObject.put("availableSeats", Plane.getSize()[0]*Plane.getSize()[1]);
            jsonObject.put("departureCity", "Detroit");
            jsonObject.put("destinationCity",Utilities.international[r.nextInt(Utilities.international.length)]);
            jsonObject.put("departureDate", departureDate.toString());
            jsonObject.put("arrivalDate", arrivalDate.toString());
            jsonObject.put("price", price);
            jsonObject.put("smoking", (r.nextInt(1) < 10 ? true : false));
            jsonObject.put("petsAllowed", (r.nextInt(1) < 5 ? true : false));
            jsonObject.put("seats", Arrays.deepToString(seats));

            jsonFlights.add(jsonObject);

        }
        for (int i = 0; i < 100; i++) { //want to create 500 flight

            Map jsonObject = new LinkedHashMap<>();

            Date departureDate = new Date(ThreadLocalRandom.current().nextLong(between1.getTime(), between2.getTime()));
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(departureDate);
            calendar.add(Calendar.HOUR_OF_DAY, r.nextInt(12)+12);

            Date arrivalDate = calendar.getTime();

            Double price = Double.parseDouble(new DecimalFormat("#.##").format(ThreadLocalRandom.current().nextDouble(800.00,1650.00)));

            boolean seats[][] = new boolean[Plane.getSize()[0]][Plane.getSize()[1]];

            for (boolean[] seat : seats) 
                Arrays.fill(seat, false);


            jsonObject.put("uuid", UUID.randomUUID().toString());
            jsonObject.put("airline", Airlines.values()[r.nextInt(Airlines.values().length)].toString());
            jsonObject.put("availableSeats", Plane.getSize()[0]*Plane.getSize()[1]);
            jsonObject.put("departureCity", "Chicago");
            jsonObject.put("destinationCity",Utilities.international[r.nextInt(Utilities.international.length)]);
            jsonObject.put("departureDate", departureDate.toString());
            jsonObject.put("arrivalDate", arrivalDate.toString());
            jsonObject.put("price", price);
            jsonObject.put("smoking", (r.nextInt(1) < 10 ? true : false));
            jsonObject.put("petsAllowed", (r.nextInt(1) < 5 ? true : false));
            jsonObject.put("seats", Arrays.deepToString(seats));

            jsonFlights.add(jsonObject);

        }

        for (int i = 0; i < 100; i++) { //want to create 500 flight

            Map jsonObject = new LinkedHashMap<>();

            Date departureDate = new Date(ThreadLocalRandom.current().nextLong(between1.getTime(), between2.getTime()));

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(departureDate);
            calendar.add(Calendar.HOUR_OF_DAY, r.nextInt(12)+12);

            Date arrivalDate = calendar.getTime();

            Double price = Double.parseDouble(new DecimalFormat("#.##").format(ThreadLocalRandom.current().nextDouble(800.00,1650.00)));

            boolean seats[][] = new boolean[Plane.getSize()[0]][Plane.getSize()[1]];

            for (boolean[] seat : seats) 
                Arrays.fill(seat, false);


            jsonObject.put("uuid", UUID.randomUUID().toString());
            jsonObject.put("airline", Airlines.values()[r.nextInt(Airlines.values().length)].toString());
            jsonObject.put("availableSeats", Plane.getSize()[0]*Plane.getSize()[1]);
            jsonObject.put("departureCity", Utilities.international[r.nextInt(Utilities.international.length)]);
            jsonObject.put("destinationCity",Utilities.international[r.nextInt(Utilities.international.length)]);
            jsonObject.put("departureDate", departureDate.toString());
            jsonObject.put("arrivalDate", arrivalDate.toString());
            jsonObject.put("price", price);
            jsonObject.put("smoking", (r.nextInt(1) < 10 ? true : false));
            jsonObject.put("petsAllowed", (r.nextInt(1) < 5 ? true : false));
            jsonObject.put("seats", Arrays.deepToString(seats));

            jsonFlights.add(jsonObject);

        }

        FileWriter f = new FileWriter(PLANE_JSON_PATH);
        f.write(jsonFlights.toJSONString().replace("},{", "},\n{"));
        f.flush();
    }
    
}
