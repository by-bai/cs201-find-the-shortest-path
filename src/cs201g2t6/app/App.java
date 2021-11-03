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
        double maxDistance = 10.0; 

        /** Find restaurants above this rating (inclusive) */ 
        double minRating = 4.0; 

        /** Set distance for two businesses to be considered neighbours */
        double neighbourDistance = 0.4;

        /** Enter algorithm:
         * 0 - BruteForce 
         * 1 - Dijkstra with Priority Queue
         * 2 - Bellman 
         * 3 - Dijkstra with Adjacency List*/ 
        int algo = 3; 
        
        try {
            List<Business> allBusinessList = FileReader.readFile("data/business.csv");
            List<Business> nearbyBusinessList = FilterBusinesses.getBusinessesNearby(allBusinessList, userLocation, maxDistance, minRating); 
            List<Business> nearbyRestaurantsList = FilterBusinesses.getRestaurantsOnly(nearbyBusinessList); 
            List<Business> nearbyHighestRatedRestaurantsList = FilterBusinesses.getHighestRatedRestaurants(nearbyRestaurantsList, minRating); 

            //System.out.println(nearbyHighestRatedRestaurantsList);

            // make user location a business
            Business user = new Business(null, "User", userLocation[0], userLocation[1], null, 0); 

            // add user into business list
            nearbyBusinessList.add(user); 

            Graph2 graph = new Graph2(nearbyBusinessList.size(), nearbyBusinessList, neighbourDistance);

            long startTime = System.currentTimeMillis();

            if (algo == 0) {
                BruteForce bruteForce = new BruteForce(graph, user);
                bruteForce.doBruteForce();
            } else if (algo == 1) {
                DijkstraPQ dijkstraPQ = new DijkstraPQ(graph, user);    
                dijkstraPQ.doDijkstraPQ();
            } else if (algo == 2) {
                BellmanFord bellmanFord = new BellmanFord(graph, user);
                bellmanFord.doBellmanFord();
            } else if (algo == 3) {
                // Dijkstra with Adjacency List //
                ArrayList<Vertex> vertices = new ArrayList<>();
                vertices.add(new Vertex(user));

                for (int i = 0; i < nearbyBusinessList.size(); i++) {
                    vertices.add(new Vertex(nearbyBusinessList.get(i)));
                }

                for (int i = 0; i < vertices.size() - 1; i++) {
                    for (int j = i + 1; j < vertices.size(); j++) {
                        double distance = CalculateDistance.calculateDistanceInKilometer(vertices.get(i).getBusiness().getLatitude(), vertices.get(i).getBusiness().getLongitude(),
                        vertices.get(j).getBusiness().getLatitude(), vertices.get(j).getBusiness().getLongitude());
    
                        if (distance <= 0.4) {
                            vertices.get(i).addAdjacentEdge(new Edge(vertices.get(i), vertices.get(j), distance));
                        }
                    }
                }

                Dijkstra dijkstra = new Dijkstra(vertices.get(0));
                dijkstra.computePath();

                double minDist = Double.MAX_VALUE;
                int index = 0;

                for (int i = 0; i < vertices.size(); i++) {
                    if (nearbyHighestRatedRestaurantsList.size() != 0) {
                        for (int j = 0; j < nearbyHighestRatedRestaurantsList.size(); j++) {
                            if (vertices.get(i).getBusiness().equals(nearbyHighestRatedRestaurantsList.get(j))) {

                                List<Vertex> path= dijkstra.shortestPath(vertices.get(0), vertices.get(i));

                                if (vertices.get(i).getWeight() != 0 && vertices.get(i).getWeight() < minDist) {
                                    minDist = vertices.get(i).getWeight();
                                    index = i;
                                }
                            }
                        } 
                    }
                }

                List<Vertex> path= dijkstra.shortestPath(vertices.get(0), vertices.get(index));

                System.out.println("The nearest restaurant to you is : " + vertices.get(index).getBusiness().getName());
                System.out.println("Location : " + vertices.get(index).getBusiness().getLatitude() + ", " + vertices.get(index).getBusiness().getLongitude());
                System.out.println("Categories : " + vertices.get(index).getBusiness().getCategories());
                System.out.println("Stars : " + vertices.get(index).getBusiness().getStars());
                System.out.format("Total distance: %.2f km\n", vertices.get(index).getWeight());
                System.out.println("Directions : " + path);
                System.out.println();
                // END //
            }

            long endTime = System.currentTimeMillis();
            long elapsed = endTime - startTime;
            System.out.println("Time elapsed: " + elapsed);
            
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist!"); 
        } 
       
       
    }
}