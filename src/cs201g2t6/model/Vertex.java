package cs201g2t6.model;

import java.util.*;

public class Vertex implements Comparable<Vertex> {
    //vertex label
    Business business;
    //Distance value               
    double distance = Double.MAX_VALUE;
    //to mark vertex as visited           
    boolean visited;
    //Edges connected to the vertex, will lead to next vertex             
    List<Edge> adjacentEdge = new ArrayList<>(); 
    //distance value is updated by which vertex 
    Vertex predecessor = null;   

    double weight = 0.0;

    public Vertex(Business business){
        this.business = business;
    }

    public void addAdjacentEdge(Edge e){
        adjacentEdge.add(e);
    }

    //Function to compare two vertices on basis of diatance
    //used by the priority queue
    @Override
    public int compareTo(Vertex b) {
        return (int) (this.distance - b.distance);
    }

    @Override
    public String toString(){
        return business.getName();
    }

    public Business getBusiness() {
        return business;
    }
    
    public List<Edge> getEdges() {
        return adjacentEdge;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
