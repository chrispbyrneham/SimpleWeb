package com.byrneham.mongodb;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class RestaurantHelper {

    public static List<Restaurant> getRestaurants(FindIterable<Document> iterable, final Gson gson ){
        final List<Restaurant> restaurants = new ArrayList<Restaurant>();
        iterable.forEach(new Block<Document>() {
            public void apply(final Document document) {
                try {
                    // MongoDb seems to cause some problems with it's document.toJson()
                    // It has embedded things like $oid and $date (bson) which confuse gson at least
                    // needing a custom deserialiser...
                    Restaurant restaurant = gson.fromJson( document.toJson(),Restaurant.class );
                    restaurants.add(restaurant);
                    System.out.println( document.toString() );//System.out.println(document);
                    String json = gson.toJson(restaurant);
                    System.out.println(json);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            }
        });
        return restaurants;
    }
}
