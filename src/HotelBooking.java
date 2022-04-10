package src;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;
import org.json.simple.parser.ParseException;
/**
 * this contains the information for a hotel booking
 */
public class HotelBooking extends Booking {
    
    private int[] index = new int[3];

    private Date date;
    /**
     * This creates hotel booking 
     * @param uuid this is the ID of the hotel booking
     * @param int is the specific room that chosen 
     * @param date is the date the room is booked for
     */
    public HotelBooking(UUID uuid, int[] index, Date date) {

        super(uuid);
        if (index.length == 3)
            this.index = index;
        this.date = date;
    }
    /**
     * this method gets the specific room that is chosen
     * @return returns the specific room
     */
    public int[] getIndex(){
        return this.index;
    }
    /**
     * this method gets the date the person is staying for
     * @return returns that date that chosen
     */
    public Date getDate(){
        return this.date;
    }
    /**
     * This constructor return a string of the hotel booking
     * @return returns the string of the hotel booking
     */
    public String toString() {
        String ret = "";

        try {
            ret += BookingManager.getInstance().getHotels().get(this.uuid).getHotelInfo() + "\n\n On date: " + this.date.toString();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (java.text.ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ret;
    }
}