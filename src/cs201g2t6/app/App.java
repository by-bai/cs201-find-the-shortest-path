package cs201g2t6.app;

import cs201g2t6.utils.*;
import cs201g2t6.model.*;

import java.util.*;
import java.io.FileNotFoundException;

public class App {
    public static void main(String[] args) {

        /** Enter x & y coordinates of your location */ 
        Double[] userLocation = {45.5309581, -122.8237802};//{40.0175444, -105.28335}; 

        /** Find restaurants within this distance */ 
        double maxDistance = 2.0; 

        /** Find restaurants above this rating (inclusive) */ 
        double minRating = 4.0; 

        /** Set distance for two businesses to be considered neighbours */
        double neighbourDistance = 0.4;
        
        try {
            List<Business> allBusinessList = FileReader.readFile("data/business.csv");
            List<Business> nearbyBusinessList = FilterBusinesses.getBusinessesNearby(allBusinessList, userLocation, maxDistance); 
            //List<Business> nearbyRestaurantsList = FilterBusinesses.getRestaurantsOnly(nearbyBusinessList); 
            //List<Business> nearbyHighestRatedRestaurantsList = FilterBusinesses.getHighestRatedRestaurants(nearbyRestaurantsList, minRating); 
            //System.out.println(nearbyHighestRatedRestaurantsList);

             // aks implementation

            // MAKE USERLOCATION A BIZ SET EVERYTHING BUT LONG , LAT TO NULL 
            Business user = new Business(null, "user", userLocation[0], userLocation[1], null, 0); 
            // add user into biz list
            nearbyBusinessList.add(user); 

            Graph2 graph = new Graph2(nearbyBusinessList.size(), nearbyBusinessList, neighbourDistance);
            
            /*
            // testing dijkstra
            DijkstraPQ dijkstraPQ = new DijkstraPQ(graph, user); 
            dijkstraPQ.doDijkstraPQ();
            */

            long startTime = System.currentTimeMillis();

            // testing bellman ford
            BellmanFord bellmanFord = new BellmanFord(graph, user);
            bellmanFord.doBellmanFord();

            long endTime = System.currentTimeMillis();
            long elapsed = endTime - startTime;
            System.out.println("Time elapsed: " + elapsed);
            
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist!"); 
        } 
       
       
    }
}