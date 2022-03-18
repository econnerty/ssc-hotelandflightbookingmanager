package src;

import java.util.UUID;

public class FlightBooking extends Booking {

    private int[] index = new int[2];

    public FlightBooking(UUID uuid, int[] index) {
        super(uuid);
        
        if (index.length == 2)
            this.index = index;

    }

    public String toString() {
        return this.uuid.toString() + " " + index.toString();
    }
    
}
