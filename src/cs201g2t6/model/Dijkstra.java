package cs201g2t6.model;

import java.util.*;

public class Dijkstra {
    //source vertex
    Vertex source;
    //Priority queue to queue vertices w.r.t least distance
    PriorityQueue<Vertex> vertexQueue = new PriorityQueue<>();

    public Dijkstra(Vertex source) {
        this.source = source;
    }

    //Function implementing Dijkstra's Algorithm
    public void computePath(){
        //Starting vertex distance should be 0
        source.distance = 0.0;         

        //add source vertex to the queue
        vertexQueue.add(source);

        //run until queue is not empty
        while(!vertexQueue.isEmpty()){  
        //Get vertex from the top of queue 
        //i.e. vertex with least distance   
        Vertex actualVertex = vertexQueue.peek();    

        //get adjacent vertices through connected edges
        for(Edge edge: actualVertex.adjacentEdge){
            Vertex v = edge.to;

            //If not visited then check whether the distance value could be lower
            if(!v.visited){
                if(actualVertex.distance + edge.weight < v.distance){
                    v.distance = actualVertex.distance + edge.weight;
                    v.predecessor =  actualVertex;     //also update its predecessor
                    vertexQueue.add(v);
                }
            }
        }

            //remove vertex from queue
            vertexQueue.poll(); 
            //mark vertex as visited                  
            actualVertex.visited = true;         
        }
    }

    //Function to return the shortest path
    public List<Vertex> shortestPath(Vertex src, Vertex dst){
        //list to hold path
        List<Vertex> shortPath = new ArrayList<>();
        //double weight = dst.getWeight();

        //Trace route from the 'v' to the source vertex
        shortPath.add(dst);
        while(dst.predecessor != null){
            shortPath.add(dst.predecessor);
            //weight += dst.distance;
            dst.setWeight(dst.distance);
            dst = dst.predecessor;
        }

        //reverse the list to correct the order
        Collections.reverse(shortPath);

        // if (weight != 0.0) {
        //     System.out.format("Total distance: %.2f km", weight);
        // } else {
        //     System.out.print("No path available");
        // }
        
        
        //return list
        return shortPath;
    }


    
    
}
