package src;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

import org.json.simple.parser.ParseException;
/**
 * FlightBooking class that extends Booking - 
 * specific for flights
 */

public class FlightBooking extends Booking {

    private int[] index = new int[2];

    /**
     * FlightBooking constructor
     * @param uuid
     * @param index
     */
    public FlightBooking(UUID uuid, int[] index) {
        super(uuid);
        
        if (index.length == 2)
            this.index = index;

    }

    /**
     * returns index
     * @return int[]
     */
    public int[] getIndex(){
        return this.index;
    }

    /**
     * returns information about flight booking as String
     * @return String
     */
    public String toString() {
        String ret = "";
        try {
            ret += BookingManager.getInstance().getPlanes().get(this.uuid).getFlightInfo() + "\n\t Your seat is ";
            switch (index[1]) {
                case 0:
                    ret += 'A';
                    break;
                case 1:
                    ret += 'B';
                    break;
                case 2:
                    ret += 'C';
                    break;
                case 3:
                    ret += 'D';
                    break;
            }
            ret += index[0]+1;
        } catch (IOException | ParseException | java.text.ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ret;
    }
    
}
