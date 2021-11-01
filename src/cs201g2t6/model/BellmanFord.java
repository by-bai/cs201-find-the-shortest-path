package cs201g2t6.model;

import java.util.*;
import cs201g2t6.utils.*;
import cs201g2t6.model.*;

public class BellmanFord {
    private Graph2 graph;
    private Business userLocation;
    private List<Business> nearbyBusinessList;
    private int size;
    
    public BellmanFord(Graph2 graph, Business userLocation) {
        // initialise all class variables
        this.graph = graph;
        this.userLocation = userLocation;
        nearbyBusinessList = graph.getBusinessList(); 
        size = nearbyBusinessList.size();
    }

    public double calcDistance(Business a, Business b) {
        return CalculateDistance.calculateDistanceInKilometer(a.getLatitude(), a.getLongitude(), b.getLatitude(), b.getLongitude());
    }
   
    public boolean checkRestaurant(Business b) {
        List<String> categories = b.getCategories();
        if (categories == null) {
            return false;
        }
        for (int i = 0; i < categories.size()-1; i++) {
            if (categories.get(i).contains("Restaurants")) {
                return true;
            }
        }
        return false;
    }
    
    public void doBellmanFord() {
        double[] costs = new double[size]; // to store distance vectors from source to node
        Business[] previous = new Business[size]; // to store previous node in path
        double minCost = 1000.0; 
        int minIndex = -1; // index of shortest path vertex

        // initialise to all costs to 1000, which represents infinity
        for (int i = 0; i < size-1; i++) {
            costs[i] = 1000.0;
            previous[i] = null;
        } 

        costs[size-1] = 0; // distance to source is 0
        
        // initialise direct costs of neighbours to source
        List<Business> sourceNeighbours = graph.getNeighboursOfBusiness(userLocation);
        for (Business n:sourceNeighbours) {
            int neighbourIndex = nearbyBusinessList.indexOf(n);
            costs[neighbourIndex] = calcDistance(userLocation, n);
            previous[neighbourIndex] = userLocation;
        }
        System.out.println();

        // for each vertex in nearbyBusinessList
        for (int i = 0; i < size-1; i++) { 
            Business vertex = nearbyBusinessList.get(i);
            if (vertex.equals(userLocation)) {
                break;
            }
            List<Business> neighbours = graph.getNeighboursOfBusiness(vertex);

            // for each edge of the current vertex
            for (Business n:neighbours) {
                int neighbourIndex = nearbyBusinessList.indexOf(n);
                // temp (cost of source to neighbour) = direct cost to vertex + cost of vertex to neighbour
                double temp = costs[i] + calcDistance(vertex, n);
                if (temp < costs[neighbourIndex]) {
                    costs[neighbourIndex] = temp;
                    previous[neighbourIndex] = vertex;
                }
            }
            if (costs[i] < minCost && checkRestaurant(vertex)) { 
                minCost = costs[i];
                minIndex = i;
            }
        }

        /*
        // testing
        for (int i = 0; i < size; i++) {
            if (costs[i] == 1000.0) {
                continue;
            }
            System.out.printf("%3d. %-50s %-10.2f",  i+1, nearbyBusinessList.get(i).getName(), costs[i]);
            if (previous[i] != null) {
                System.out.print(previous[i].getName());
            }
            System.out.println();
        }
        */

        if (minIndex == -1) {
            System.out.println("No destination found");
            return;
        }

        Business destination = nearbyBusinessList.get(minIndex);

        // generate shortest path
        String path = "";
        Business curr = destination;
        int currIndex = 0;

        while (curr != userLocation) {
            path = " -> " + curr.getName() + path;
            currIndex = nearbyBusinessList.indexOf(curr);
            curr = previous[currIndex];
        }
        path = "User" + path;

        // output results
        System.out.println("The nearest restaurant to you is: " + destination.getName());
        System.out.println("Location: " + destination.getLatitude() + ", " + destination.getLongitude());
        System.out.println("Categories: " + destination.getCategories());
        System.out.println("Stars: " +  destination.getStars());
        System.out.println("Directions: " + path);

        /*
        // pseudocode

        function bellmanFord(G, S)
        for each vertex V in G
            distance[V] <- infinite
            previous[V] <- NULL
        distance[S] <- 0

        for each vertex V in G				
            for each edge (U,V) in G
            tempDistance <- distance[U] + edge_weight(U, V)
            if tempDistance < distance[V]
                distance[V] <- tempDistance
                previous[V] <- U

        return distance[], previous[]
        */
    }
}
