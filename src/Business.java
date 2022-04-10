package src;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
/**
 * Stores a business and the rating associate
 */
public class Business implements src.JSON {

    private UUID uuid;
    private String name;
    private double rating;
    private int numRatings;
    /**
     * This creates a business and has a UUID
     * @param uuid is the id of the business 
     * @param name is the name of the business
     * @param rating is the rating of the business
     * @param numRating is the amount of ratings there are for the business
     */
    public Business(UUID uuid, String name, double rating, int numRatings) {
        this.uuid = uuid;
        this.name = name;
        this.rating = rating;
        this.numRatings = numRatings;//should start as zero in the implementing method for a new Business.
    }
    /**
     * This adds the rating to the business
     * @param name is the name of the business
     * @param newRating is the new rating that is being added to the business
     */
    public double addRating(String name, Double newRating) {
        if(rating > 5 || rating <0) 
            return -1;
        this.rating = (numRatings*rating+newRating)/(numRatings+1);
        this.numRatings++;
        return this.rating;
    }
    /**
     * This constructor sets the rating for the business
     * @param uuid is the id of the business 
     * @param name is the name of the business
     * @param rating is the rating of the business
     * @param numRating is the amount of ratings there are for the business
     */
    public void setRating(UUID uuid, String name, Double rating, int numRatings) {
        if(rating <= 5 || rating >=0) {
            this.uuid = uuid;
            this.name = name;
            this.rating = rating;
            this.numRatings = numRatings;
        }
    }
    /**
     * This constructor gets UUID
     * @return UUID
     */
    public UUID getUUID() {
        return this.uuid;
    }
    /**
     * This constructor sets the name of the business
     * @return This return the name of the businesss
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * This constructor gets name of the business
     * @return this returns the name of the business 
     */
    public String getName() {
        return this.name;
    }
    /**
     * This constructor gets the rating 
     * @return this return the rating of the business
     */
    public double getRating() {
        return this.rating;
    }
    /**
     * This constructor gets number of ratings
     * @return this return the rating of the business
     */
    public int getnumRatings() {
        return this.numRatings;
    }
    /**
     * This return JSON object which can be written to a JSON file
     * @return This returns the JSON object 
     */
    public Map toJsonObject() {
        
        Map jsonObject = new LinkedHashMap<>();

        jsonObject.put("UUID",this.uuid);
        jsonObject.put("name",this.name);
        jsonObject.put("numRatings", this.numRatings);
        jsonObject.put("rating",this.rating);
        
        return jsonObject;    
    }
    /**
     * This prints out the business string
     * @return this return the string 
     */
    public String toString() {
        return "Business [name: " + name + ", rating: " + rating + ", number of ratings: " + numRatings + ", uuid: " + uuid + "]";
    }
}