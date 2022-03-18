package src;

import java.util.UUID;

public class HotelBooking extends Booking {
    
    private int[] index = new int[3];

    public HotelBooking(UUID uuid, int[] index) {

        super(uuid);

        if (index.length == 3)
            this.index = index;

    }

}
