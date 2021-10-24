package cs201g2t6.model;

import java.util.List;

public class Node<E> {

    /** The element stored at this node */
    private Business business; // reference to the element stored at this node

    /** A reference to the subsequent node in the list */
    private List <Node<E>> neighbours; // reference to the subsequent node in the list

    /**
     * Creates a node with the given element and next node.
     *
     * @param biz    the element to be stored
     * @param beside reference to list of nodes neighbouring the new node
     */
    public Node(Business biz, List<Node<E>> beside) {
        business = biz;
        neighbours = beside;
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

    /**
     * Returns list of nodes neighbouring the new node .
     * 
     * @return the following node
     */
    public List<Node<E>> getNeighbours() {
        return neighbours;
    }

    // Modifier methods
    /**
     * Sets the node's list of neighbouring nodes
     * 
     * @param n the node's list of neighbouring nodes
     */
    public void setList(List<Node<E>> n) {
        neighbours = n;
    }
} 
