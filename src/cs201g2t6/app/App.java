package cs201g2t6.app;

import cs201g2t6.utils.*;
import cs201g2t6.model.Business;
import cs201g2t6.model.DijkstraPQ;
import cs201g2t6.model.Graph;
import cs201g2t6.model.Graph2;
import cs201g2t6.model.Node;
import cs201g2t6.model.Test;
import cs201g2t6.model.priQueue;
import cs201g2t6.model.priQueueComparator;

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
        
        try {
            List<Business> allBusinessList = FileReader.readFile("data/businesstest10.csv");
            List<Business> nearbyBusinessList = FilterBusinesses.getBusinessesNearby(allBusinessList, userLocation, maxDistance); 
            List<Business> nearbyRestaurantsList = FilterBusinesses.getRestaurantsOnly(nearbyBusinessList); 
            List<Business> nearbyHighestRatedRestaurantsList = FilterBusinesses.getHighestRatedRestaurants(nearbyRestaurantsList, minRating); 

            //System.out.println(nearbyHighestRatedRestaurantsList);
            System.out.println("doing");

        // aks implementation

            // MAKE USERLOCATION A BIZ SET EVERYTHING BUT LONG , LAT TO NULL 
            Business user = new Business("aks","aks", userLocation[0], userLocation[1],null, 0); 
            // add user into biz list
            nearbyBusinessList.add(user); 

            // Graph2 graph = new Graph2(nearbyBusinessList.size(), nearbyBusinessList);
            // DijkstraPQ dijkstraPQ = new DijkstraPQ(graph, user); 
            // dijkstraPQ.doDijkstraPQ();
            
            
            
            // takes in 10 nodes, is undirected and weighted
            // Graph graph = new Graph(10, false, true);




// viddya graph implementation
            // // for each business, limit to 10
            // for (int i = 0; i < 8; i++) {
            //     // check with everything that comes after it
            //     for (int j = 1; j < 9; j++) {

            //         double distance = CalculateDistance.calculateDistanceInKilometer(nearbyBusinessList.get(i).getLatitude(), nearbyBusinessList.get(i).getLongitude(),
            //         nearbyBusinessList.get(j).getLatitude(), nearbyBusinessList.get(j).getLongitude());
            //         // if distance between them is less than 3 then they are conencted by an edge
            //         if (distance <= 3.0) {
            //             // node only takes in the business in the source node, i'll see what I can do about this later - Viddya
            //             graph.addEdge(i, j, new Node(nearbyBusinessList.get(i), distance));  
            //         }

            //     }
            // }

           

            // System.out.println();
            // // displays weight of edges
            // graph.printMatrix();
            
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist!"); 
        } 
       
       
    }
}