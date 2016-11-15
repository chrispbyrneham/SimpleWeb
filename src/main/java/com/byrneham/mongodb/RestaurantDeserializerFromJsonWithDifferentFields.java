package com.byrneham.mongodb;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.Date;

public class RestaurantDeserializerFromJsonWithDifferentFields implements JsonDeserializer<Restaurant> {


    @Override
    public Restaurant deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        JsonObject jObject = jsonElement.getAsJsonObject();
        Restaurant restaurant = new Restaurant();
        String borough = jObject.get("borough").getAsString();
        String cuisine = jObject.get("cuisine").getAsString();
        String name = jObject.get("name").getAsString();
        String id = jObject.get("restaurant_id").getAsString();
        restaurant.setBorough(borough);
        restaurant.setCuisine(cuisine);
        restaurant.setName(name);
        restaurant.setRestaurant_id(id);

        JsonObject jsonAddress = jObject.get("address").getAsJsonObject();
        String building = jsonAddress.get("building").getAsString();
        JsonArray jsonCoords = jsonAddress.get("coord").getAsJsonArray();
        double longit,latit;
        longit = latit = 0.0;
        if( jsonCoords.size() > 0){
            longit = jsonCoords.get(0).getAsDouble();
        }
        if( jsonCoords.size() > 1) {
            latit = jsonCoords.get(1).getAsDouble();
        }

        String street = jsonAddress.get("street").getAsString();
        String zipcode = jsonAddress.get("zipcode").getAsString();

        Address address = new Address();
        address.setBuilding(building);
        address.setStreet(street);
        address.setZipcode(zipcode);
        address.setCoord(new Double[]{longit,latit});
        restaurant.setAddress(address);
        JsonArray jsonGrades = jObject.get("grades").getAsJsonArray();

        Grade[] grades = new Grade[jsonGrades.size()];

        for( int i = 0; i < jsonGrades.size(); i++ ){
            JsonObject jsonGrade = jsonGrades.get(i).getAsJsonObject();
            String gradeStr = jsonGrade.get("grade").getAsString();
            JsonElement gradeElement = jsonGrade.get("score");
            String score = null;
            if( !(gradeElement instanceof JsonNull) ) { // gradeElement.isJsonNull()
                score = gradeElement.getAsString();
            }
            JsonObject jsonDate = jsonGrade.get("date").getAsJsonObject();
            long time = jsonDate.get("$date").getAsLong();
            Date date = new Date(time);
            Grade grade = new Grade();
            grade.setGrade(gradeStr);
            grade.setScore(score);
            grade.setDate(date);
            grades[i]=grade;
        }

        restaurant.setGrades(grades);
        return restaurant;
    }
}
