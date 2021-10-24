package cs201g2t6.utils;

import java.io.*;
import java.util.*; 
import cs201g2t6.model.Business;

public class FilterRestaurants {
    /* Fiilter restaurants only */

    public FilterRestaurants() {

    }

    public static List<Business> getRestaurantsOnly(List <Business> businessList) {

        List<Business> restaurantsList = new ArrayList<>();
        
        for (int i = 0; i < businessList.size(); i++) {
            Business business = businessList.get(i); 
            if (business.getCategories().contains("Restaurants")) {
                restaurantsList.add(business); 
            }
        }

        System.out.println(restaurantsList);

        return restaurantsList;

    }

}
