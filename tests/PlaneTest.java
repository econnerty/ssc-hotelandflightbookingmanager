package tests;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import src.Plane;
import src.Airlines;

class PlaneTest {
	private Plane plane;
	
	 @BeforeEach
	    public void setup() {
	        plane = new Plane(null, Airlines.AMERICAN_AIRLINES, 80, 494.6,new Date(), new Date(), "Henderson", "Louisville", false, false, null, null);
	    }

	    @AfterEach
	    public void tearDown() {

	    }

	    @Test
	   public void testValidPlanes() {
	    	plane = new Plane(null, null, 0, 0.0, null, null, null, null, false, false, null, null);
	    	assertEquals("Doesn't exist", plane.getFlightInfo());
	    	
	    }

	    @Test
	    public void testFlightDepartureCity() {
	    	plane = new Plane(null, null, 0, 0.0, null, null, null, null, false, false, null, null);
	    	assertEquals("Invalid departure dity", plane.getDepartureCity());

	    }
	    public void testAmountOfSeats() {
	    	plane = new Plane(null, null, -40, 0.0, null, null, null, null, false, false, null, null);
	    	assertEquals("Invalid amount of seats", plane.getSize());

	    }
}
