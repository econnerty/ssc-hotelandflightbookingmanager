package src;

public abstract class Booking {

    protected String uuid;
    protected int[] index;

    public Booking(String uuid){
        this.uuid = uuid;
    }

    public int[] getIndex() {
        return index;
    }

    public class FlightBooking extends Booking {

        public FlightBooking(String uuid, int[] index){
            super(uuid);
            if (index.length == 2)
                this.index = index;
        }
    
    }

    public class HotelBooking extends Booking {

        public HotelBooking(String uuid, int[] index){
            super(uuid);
            if (index.length == 3)
                this.index = index;
        }
    
    }
    
}


