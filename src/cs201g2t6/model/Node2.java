package cs201g2t6.model;

import java.util.*;

public class Node2 {
    private Vector<Integer> path = null;
    private int value;

    public Node2(Vector<Integer> parentPath, int nodeValue) {
        path = new Vector<Integer>();
        if(parentPath != null) {
            for (int vertex: parentPath) {
                path.add(vertex);
            }
        }

        path.add(nodeValue);
        this.value = nodeValue;
    }
    
    public Vector<Integer> getPath() {
        return path;
    }

    public int getValue() {
        return value;
    }

    public double calculateWeight(double[][] adj) {
        double result = 0;
        if (adj!=null && path != null && path.size() > 1) {
            double totalWeight = 0;
            for (int i = 1; i < path.size(); i++) {
                int v1 = path.get(i-1);
                int v2 = path.get(i);
                if(v1 >= 0 && v2 >= 0 && v1 < adj.length && v2 < adj.length) {
                    double weight = adj[v1][v2];
                    totalWeight = totalWeight + weight;
                } else {
                    System.out.println("Parameter not valid");
                    totalWeight = 0;
                    break;
                }
                result = totalWeight;
            } 
        } 
        return result;
    }

    public void print(double[][] adj, List<Business> nearbyBusinessList) {
        if (path != null && path.size() > 0) {
            System.out.print(nearbyBusinessList.get(path.get(path.size() - 1)).getName());
            for (int i = path.size() - 2; i >= 0; i-- ) {
                System.out.print(" -> " + nearbyBusinessList.get(path.get(i)).getName());
            }
            System.out.println("");
        } else {
            System.out.println("NULL");
        }
    }

    public void printDestination(double[][] adj, List<Business> nearbyBusinessList) {
        if (path != null && path.size() > 0) {
            System.out.println(nearbyBusinessList.get(path.get(0)).getName());
        } else {
            System.out.println("NULL");
        }
    }

    public void printLocation(double[][] adj, List<Business> nearbyBusinessList) {
        if (path != null && path.size() > 0) {
            System.out.print(nearbyBusinessList.get(path.get(0)).getLatitude() + ", ");
            System.out.println(nearbyBusinessList.get(path.get(0)).getLongitude());
        } else {
            System.out.println("NULL");
        }
    }

    public void printCategories(double[][] adj, List<Business> nearbyBusinessList) {
        if (path != null && path.size() > 0) {
            System.out.println(nearbyBusinessList.get(path.get(0)).getCategories());
            
        } else {
            System.out.println("NULL");
        }
    }

    public void printStars(double[][] adj, List<Business> nearbyBusinessList) {
        if (path != null && path.size() > 0) {
            System.out.println(nearbyBusinessList.get(path.get(0)).getStars());
        } else {
            System.out.println("NULL");
        }
    }
}
