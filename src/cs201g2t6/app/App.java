package cs201g2t6.app;

import cs201g2t6.utils.*;
import cs201g2t6.model.*;
import java.util.*;
import java.io.FileNotFoundException;

public class App {
    public static void main(String[] args) {

        /** Enter x & y coordinates of your location */ 
        Double[] userLocation = {40.0175444, -105.28335}; 

        /** Find restaurants within this distance */ 
        double maxDistance = 2.0; 

        /** Find restaurants above this rating (inclusive) */ 
        double minRating = 4.0; 

        /** Set distance for two businesses to be considered neighbours */
        double neighbourDistance = 0.4;

        /** Enter algorithm:
         * 1 - Dijkstra with Priority Queue
         * 2 - Bellman */ 
        int algo = 2; 
        
        try {
            List<Business> allBusinessList = FileReader.readFile("data/businesstest10.csv");
            List<Business> nearbyBusinessList = FilterBusinesses.getBusinessesNearby(allBusinessList, userLocation, maxDistance, minRating); 
            //List<Business> nearbyRestaurantsList = FilterBusinesses.getRestaurantsOnly(nearbyBusinessList); 
            //List<Business> nearbyHighestRatedRestaurantsList = FilterBusinesses.getHighestRatedRestaurants(nearbyRestaurantsList, minRating); 

            //System.out.println(nearbyHighestRatedRestaurantsList);

            // make user location a business
            Business user = new Business(null, "user", userLocation[0], userLocation[1], null, 0); 

            // add user into business list
            nearbyBusinessList.add(user); 

            Graph2 graph = new Graph2(nearbyBusinessList.size(), nearbyBusinessList, neighbourDistance);

            long startTime = System.currentTimeMillis();

            if (algo == 1) {
                DijkstraPQ dijkstraPQ = new DijkstraPQ(graph, user);    
                dijkstraPQ.doDijkstraPQ();
            } else if (algo == 2) {
                BellmanFord bellmanFord = new BellmanFord(graph, user);
                bellmanFord.doBellmanFord();
            }

            long endTime = System.currentTimeMillis();
            long elapsed = endTime - startTime;
            System.out.println("Time elapsed: " + elapsed);
            
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist!"); 
        } 
       
       
    }
}