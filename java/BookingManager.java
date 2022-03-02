package java;

public class BookingManager implements Manager{

    private static BookingManager bookingManager;

    private BookingManager(){

    }

    public BookingManager getInstance() {

        if (bookingManager == null)
            bookingManager = new BookingManager();

        return bookingManager;
        

    }
    
}
