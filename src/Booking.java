package src;

import java.util.UUID;

public abstract class Booking {
    protected UUID uuid;

    public Booking(UUID uuid){
        this.uuid = uuid;
    }
    
}