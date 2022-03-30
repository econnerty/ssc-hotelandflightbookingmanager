package src;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

import org.json.simple.parser.ParseException;

public abstract class Booking {
    protected UUID uuid;
    protected int[] index;

    public Booking(UUID uuid){
        this.uuid = uuid;
    }

    public Booking(UUID uuid, int[] index){
        this.uuid = uuid;
        this.index = index;
    }

    public UUID getUUID(){
        return this.uuid;
    }

    public int[] getIndex(){
        return this.index;
    }
    
    public String toString() {
        return this.uuid.toString() + index.toString();
    }
    
    
}
