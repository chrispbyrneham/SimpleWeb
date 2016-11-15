package com.byrneham.mongodb;

import java.util.Arrays;

public class Restaurant {

    Address address; //     building coord street zipcode
    String borough;
    String cuisine;
    Grade[] grades;    // date grade score
    String name;
    String restaurant_id;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getBorough() {
        return borough;
    }

    public void setBorough(String borough) {
        this.borough = borough;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public Grade[] getGrades() {
        return grades;
    }

    public void setGrades(Grade[] grades) {
        this.grades = grades;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(String restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "address=" + address +
                ", borough='" + borough + '\'' +
                ", cuisine='" + cuisine + '\'' +
                ", grades=" + Arrays.toString(grades) +
                ", name='" + name + '\'' +
                ", restaurant_id='" + restaurant_id + '\'' +
                '}';
    }
}
