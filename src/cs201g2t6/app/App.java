package cs201g2t6.app;

import cs201g2t6.utils.*;
import cs201g2t6.model.Business;
import java.util.*;
import java.io.FileNotFoundException; 

public class App {
    public static void main(String[] args) {

        /** Enter x & y coordinates of your location */ 
        Double[] userLocation = {40.0175444, -105.28335}; 

        /** Find restaurants within this distance */ 
        double maxDistance = 10.0; 

        /** Find restaurants above this rating (inclusive) */ 
        double minRating = 4.5; 
        
        try {
            List<Business> allBusinessList = FileReader.readFile("data/business.csv");
            List<Business> nearbyBusinessList = FilterBusinesses.getBusinessesNearby(allBusinessList, userLocation, maxDistance); 
            List<Business> nearbyRestaurantsList = FilterBusinesses.getRestaurantsOnly(nearbyBusinessList); 
            List<Business> nearbyHighestRatedRestaurantsList = FilterBusinesses.getHighestRatedRestaurants(nearbyRestaurantsList, minRating); 

            System.out.println(nearbyHighestRatedRestaurantsList);
            
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist!"); 
        } 
       
       
    }
}