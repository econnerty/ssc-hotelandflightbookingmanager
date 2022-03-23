package src;

import java.util.LinkedHashMap;
import java.util.Map;

public class Business implements JSON {

    private String name;
    private double rating;
    private int numRatings;



    public Map toJsonObject() {
        
        Map jsonObject = new LinkedHashMap<>();

        jsonObject.put("name",this.name);
        jsonObject.put("numRatings", this.numRatings);
        jsonObject.put("rating",this.rating);
        
        return jsonObject;    
    }
}