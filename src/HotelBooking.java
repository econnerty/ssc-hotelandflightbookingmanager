package src;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import org.json.simple.parser.ParseException;

public class HotelBooking extends Booking {
    
    private int[] index = new int[3];

    private Date date;

    public HotelBooking(UUID uuid, int[] index, Date date) {

        super(uuid);

        if (index.length == 3)
            this.index = index;

        this.date = date;

    }

    public int[] getIndex(){
        return this.index;
    }
    public Date getDate(){
        return this.date;
    }

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
