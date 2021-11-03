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

    public int calculateWeight(int[][] adj) {
        int result = 0;
        if (adj!=null && path != null && path.size() > 1) {
            int totalWeight = 0;
            for (int i = 1; i < path.size(); i++) {
                int v1 = path.get(i-1);
                int v2 = path.get(i);
                if(v1 >= 0 && v2 >= 0 && v1 < adj.length && v2 < adj.length) {
                    int weight = adj[v1][v2];
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

    public void print(int[][] adj, char[] label) {
        System.out.println("Path");
        if (path != null && path.size() > 0) {
            System.out.print(label[path.get(0)]);
            for (int i = 1; i < path.size(); i++ ) {
                System.out.print("-" + label[path.get(i)]);
            }
            System.out.println("");
        } else {
            System.out.println("NULL");
        }
    }
}
