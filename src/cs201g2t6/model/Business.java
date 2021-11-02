package cs201g2t6.model;

import java.util.*; 

public class Business {
    private String id;
    private String name;
    private double latitude;
    private double longitude; 
    private List<String> categories; 
    private double stars; 

    public Business (String id, String name, double latitude, double longitude, List<String> categories, double stars) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.categories = categories; 
        this.stars = stars; 
    }

    public String id() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public List<String> getCategories() {
        return categories;
    }

    public double getStars() {
        return stars;
    }

    public String toString() {
        return "name=" + getName() + "\n" + 
               "latitude=" + getLatitude() + "\n" + 
               "longitude=" + getLongitude() + "\n" + 
               "categories=" + getCategories() + "\n" + 
               "stars=" + getStars() + "\n \n";
    }

    

}