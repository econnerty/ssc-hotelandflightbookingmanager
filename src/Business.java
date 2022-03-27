package src;

import java.util.LinkedHashMap;
import java.util.Map;

public class Business implements src.JSON {

    private String name;
    private double rating;
    private int numRatings;

    public Business(String name, double rating, int numRatings) {
        
    }
    public double addRating(String name, Double newRating) {
        
        this.rating = (numRatings*rating+newRating)/(numRatings+1);
        this.numRatings++;
        return this.rating;
    }
    public double getRating(String name) {

        return this.rating;
    }

    public Map toJsonObject() {
        
        Map jsonObject = new LinkedHashMap<>();

        jsonObject.put("name",this.name);
        jsonObject.put("numRatings", this.numRatings);
        jsonObject.put("rating",this.rating);
        
        return jsonObject;    
    }
}