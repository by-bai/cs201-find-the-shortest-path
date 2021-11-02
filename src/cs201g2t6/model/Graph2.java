package cs201g2t6.model;

import java.util.*;
import java.util.List;
import cs201g2t6.utils.*; 

// implementation of graph without nodes
public class Graph2 {

// instance variables

    private int numOfNodes;
    private List<Business> nearbyBusinessList; 
    // shows if two nodes are connected
    private int[][] adjMatrix; 
    // checks if two business are neighbouring/connected
    private boolean[][] isSetMatrix;

//graph2 constructor

// works
    public Graph2(int numOfNodes, List<Business> nearbyBusinessList){
        this.numOfNodes = numOfNodes;
        this.nearbyBusinessList = nearbyBusinessList;

        // Initializes our adjacency matrix to the appropriate size
        // adjancency matrix has 1 if two biz is connected and 0 if not
        adjMatrix = new int[numOfNodes][numOfNodes];
        isSetMatrix = new boolean[numOfNodes][numOfNodes];

        // index of business in biz list and in adjMatrix/isSetMatrix is same
        
        /*
        let's say your biz list has businesses A B C D E 
        so your adj matrix will look like this
     A B C D E
    A
    B
    C      x
    D    x
    E

    if C has index 2 in biz list, it will be in row 2 or column two . 
        */

        // initialize adjMatrix and isSetMatrix with values
        for(int i =0 ; i <= numOfNodes - 1; i++){
            // column biz
            Business inQuestion = nearbyBusinessList.get(i); 
            for(int j = 0 ; j<= numOfNodes -1; j++){
                // row biz
                Business compareBiz = nearbyBusinessList.get(j);
                Double distance = CalculateDistance.calculateDistanceInKilometer(inQuestion.getLatitude(),
                        inQuestion.getLongitude(), compareBiz.getLatitude(), compareBiz.getLongitude());
                // CAN CHANGE DISTANCE TO CHANGE INPUT SIZE TO EXPERIMENT
                if(distance == 0 || distance > 0.4){
                    adjMatrix[i][j] = 0;
                    isSetMatrix[i][j] = false;
                }else{
                    adjMatrix[i][j] = 1;
                    isSetMatrix[i][j] = true;
                }

            }
        }

        

    }
//works
    public List<Business> getNeighboursOfBusiness(Business E){
        List <Business> toReturn = new ArrayList<>(); 
        int index = nearbyBusinessList.indexOf(E); 
        for(int j = 0 ; j<= numOfNodes-1 ; j++){
            if(adjMatrix[index][j] == 1){
                toReturn.add(nearbyBusinessList.get(j)); 
            }
        }
        return toReturn; 

    }
    
    
    public int[][] getAdjMatrix(){
        return adjMatrix; 
    }

    public boolean[][] getIsSetMatrix() {
        return isSetMatrix;
    }

    public List<Business> getBusinessList() {
        return nearbyBusinessList;
    }

    
}
