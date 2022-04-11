/**
author: Maeko Maja
*/
package tests;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import src.Business;

class BusinessTest {
    private Business business;

    @BeforeEach
    public void setup() {
        business=new Business(null, "ooga", 5, 3);
    }

    @AfterEach
    public void tearDown() {

    }

    @Test
    void testNullValidBusiness() {
        business = new Business(null,null,0,0);
        assertEquals("", business.getName());
    }

    @Test
    void testNumRatingValidBusiness() {
        business = new Business(null,null,0,-1);
        assertEquals(0, business.getnumRatings());
    }

    @Test
    void testRatingValidBusiness() {
        business = new Business(null,null,-2,0);
        assertEquals(0, business.getRating());
    }

    @Test
    void testAddingRating() {
        business.addRating("ooga", -1.0);
        assertEquals(3, business.getnumRatings());
    }
}