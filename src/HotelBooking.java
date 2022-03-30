package src;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

import org.json.simple.parser.ParseException;

public class HotelBooking extends Booking {
    
    private int[] index = new int[3];

    public HotelBooking(UUID uuid, int[] index) {

        super(uuid);

        if (index.length == 3)
            this.index = index;

    }

    public int[] getIndex(){
        return this.index;
    }

    public String toString() {
        String ret = "";

        try {
            ret += BookingManager.getInstance().getHotels().get(this.uuid).getHotelInfo();
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
