package java;

public class BookingManager {

    private static BookingManager bookingManager;

    private BookingManager(){

    }

    public BookingManager getInstance() {

        if (bookingManager == null)
            bookingManager = new BookingManager();

        return bookingManager;
        

    }
    
}
