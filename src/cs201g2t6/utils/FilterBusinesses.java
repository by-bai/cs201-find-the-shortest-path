package cs201g2t6.utils;

import java.util.*; 
import cs201g2t6.model.Business;

public class FilterBusinesses {

    /* Returns a list of businesses within a maxDistance from user's location */
     public static List<Business> getBusinessesNearby(List<Business> businessList, Double[] userLocation, double maxDistance) {
        List<Double> distanceList = new ArrayList<>();
        List<Business> nearbyBusinessList = new ArrayList<>(); 


        for (int i = 0; i < businessList.size(); i++) {
            Business business = businessList.get(i); 
            double distanceBetweenLocations = CalculateDistance.calculateDistanceInKilometer(userLocation[0], 
                                                        userLocation[1], 
                                                        business.getLatitude(), 
                                                        business.getLongitude());

            if ((distanceBetweenLocations < maxDistance) && business.getStars() >= 4.0) {
                distanceList.add(distanceBetweenLocations);
                nearbyBusinessList.add(business); 
            }
        

        }
        
        //System.out.println(distanceList); // for testing only
        // System.out.println(nearbyBusinessList);
        return nearbyBusinessList; 


    }

    /* Returns a list of businesses that fall under the Restaurants category */
    public static List<Business> getRestaurantsOnly(List<Business> businessList) {

        List<Business> restaurantsList = new ArrayList<>();
        
        for (int i = 0; i < businessList.size(); i++) {
            Business business = businessList.get(i); 
            if (business.getCategories().contains("Restaurants")) {
                restaurantsList.add(business); 
            }
        }

        return restaurantsList;

    }

    /* Returns a list of businesses (restaurants) with stars >= minRating */
    public static List<Business> getHighestRatedRestaurants(List<Business> businessList, double minRating) {

        List<Business> highestRatedList = new ArrayList<>();

        for (int i = 0; i < businessList.size(); i++) {
            Business business = businessList.get(i); 
            
            if (business.getStars() >= minRating) {
                highestRatedList.add(business); 
            }
        }

        return highestRatedList; 
    }

   

}
