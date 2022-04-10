package src;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;
import org.json.simple.parser.ParseException;
/**
 * abstract class for Bookings for variables and
 * methods for specific Bookings
 */
public abstract class Booking {
    protected UUID uuid;
    protected int[] index;
    /**
     * Booking constructor with UUID
     * @param uuid
     */
    public Booking(UUID uuid){
        this.uuid = uuid;
    }
    /**
     * Booking constructor with uuid and index
     * @param uuid
     * @param index
     */
    public Booking(UUID uuid, int[] index){
        this.uuid = uuid;
        this.index = index;
    }
    /**
     * gets UUID
     * @return UUID
     */
    public UUID getUUID(){
        return this.uuid;
    }
    /**
     * returns index
     * @return int[]
     */
    public int[] getIndex(){
        return this.index;
    }
    /**
     * returns uuid and index of Booking as String format
     * @return String 
     */
    public String toString() {
        return this.uuid.toString() + index.toString();
    }
}