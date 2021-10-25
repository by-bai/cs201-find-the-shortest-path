package cs201g2t6.model;

import java.util.List;

public class Node {

    /** The element stored at this node */
    private Business business; // reference to the element stored at this node

    private double weight;

    /** A reference to the subsequent node in the list */
    private List <Node> neighbours; // reference to the subsequent node in the list

    /**
     * Creates a node with the given element and next node.
     *
     * @param biz    the element to be stored
     * @param beside reference to list of nodes neighbouring the new node
     */
    public Node(Business biz, List<Node> beside) {
        business = biz;
        neighbours = beside;
    }

    // not sure how to incorporate list yet - Viddya
    public Node(Business biz, double weight) {
        business = biz;
        this.weight = weight;
    }

    // Accessor methods
    /**
     * Returns the business stored at the node.
     * 
     * @return the business stored at the node
     */
    public Business getBusiness() {
        return business;
    }


    public double getWeight() {
        return weight;
    }

    /**
     * Returns list of nodes neighbouring the new node .
     * 
     * @return the following node
     */
    public List<Node> getNeighbours() {
        return neighbours;
    }

    // Modifier methods
    /**
     * Sets the node's list of neighbouring nodes
     * 
     * @param n the node's list of neighbouring nodes
     */
    public void setList(List<Node> n) {
        neighbours = n;
    }
} 
