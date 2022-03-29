package src;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

public class Business implements src.JSON {

    private UUID uuid;
    private String name;
    private double rating;
    private int numRatings;

    public Business(UUID uuid, String name, double rating, int numRatings) {
        this.uuid = uuid;
        this.name = name;
        this.rating = rating;
        this.numRatings = numRatings;//should start as zero in the implementing method for a new Business.
    }
    public double addRating(String name, Double newRating) {
        if(rating > 5 || rating <0) 
            return -1;
        this.rating = (numRatings*rating+newRating)/(numRatings+1);
        this.numRatings++;
        return this.rating;
    }
    public void setRating(UUID uuid, String name, Double rating, int numRatings) {
        if(rating <= 5 || rating >=0) {
            this.uuid = uuid;
            this.name = name;
            this.rating = rating;
            this.numRatings = numRatings;
        }
    }
    public UUID getUUID() {
        return this.uuid;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }
    public double getRating() {
        return this.rating;
    }
    public int getnumRatings() {
        return this.numRatings;
    }
    public Map toJsonObject() {
        
        Map jsonObject = new LinkedHashMap<>();

        jsonObject.put("UUID",this.uuid);
        jsonObject.put("name",this.name);
        jsonObject.put("numRatings", this.numRatings);
        jsonObject.put("rating",this.rating);
        
        return jsonObject;    
    }
}