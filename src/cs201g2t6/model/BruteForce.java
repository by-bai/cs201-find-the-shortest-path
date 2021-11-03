package cs201g2t6.model;


import java.util.*;

public class BruteForce {
    private Graph2 graph;
    private Business userLocation;
    private List<Business> nearbyBusinessList;
    private int size;

    public BruteForce(Graph2 graph, Business userLocation) {
        this.graph = graph;
        this.userLocation = userLocation;
        nearbyBusinessList = graph.getBusinessList(); 
        size = nearbyBusinessList.size();
    }

    public void doBruteForce() {
        String[] label = new String[size];
        for(int i = 0; i < size; i++) {
            label[i] =  nearbyBusinessList.get(i).getName();
        }

        int[][] matrix = graph.getAdjMatrix();

        // for (int i = 0; i < matrix.length; i++) {
        //     for (int j = 0; j < matrix[i].length; j++) {
        //         System.out.print(matrix[i][j]);
        //     }
        //     System.out.println();
        // }

        int start = 0;
        int end = size - 1;

        Queue<Node2> queue = new Queue<>();
        Node2 root = new Node2(null, start);
        queue.Add(root);

        int solution = 0;
        Node2 nearestVertex = null;
        double smallestCost = Double.MAX_VALUE;
        
        // outer:
        while (!(queue.isEmpty())) {
            Node2 originalNode = queue.peek();
            int originalVertex = originalNode.getValue();
            Vector<Integer> path = originalNode.getPath();
            // System.out.println(originalVertex);
            for (int i = 0; i < matrix[originalVertex].length; i++) {
                if (!path.contains(i)) {
                    // System.out.println(i);
                    double weight = matrix[originalVertex][i];
                    if (weight > 0) {
                        Node2 childNode = new Node2 (path, i);
                        if (i == end) {
                            System.out.print(((++solution) + "."));
                            childNode.print(matrix,label);
                            double totalWeight = childNode.calculateWeight(matrix);
                            System.out.println("Total Weight: " + totalWeight);
                    
                            if (totalWeight < smallestCost) {
                                smallestCost = totalWeight;
                                nearestVertex = childNode;

                            }
                            System.out.println();
                            // break outer;
                        } else {
                            queue.Add(childNode);
                        }

                    }
                }
            } 
            queue.poll();
        }
        System.out.println("Shortest Path:");
        nearestVertex.print(matrix,label);
        System.out.println("Total Weight: " + smallestCost);

    }
    






}
