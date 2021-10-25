package cs201g2t6.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Graph {

  private int numOfNodes;
  private boolean directed;
  private boolean weighted;
  private Node[][] matrix;

  /*
   This will allow us to safely add weighted graphs in our class since
   we will be able to check whether an edge exists without relying
   on specific special values (like 0)
  */
  private boolean[][] isSetMatrix;

  public Graph(int numOfNodes, boolean directed, boolean weighted) {

    this.directed = directed;
    this.weighted = weighted;
    this.numOfNodes = numOfNodes;

    // Simply initializes our adjacency matrix to the appropriate size
    matrix = new Node[numOfNodes][numOfNodes];
    isSetMatrix = new boolean[numOfNodes][numOfNodes];
  }

  public void addEdge(int source, int destination, Node node) {

    matrix[source][destination] = node;
    isSetMatrix[source][destination] = true;

    if (!directed) {
        matrix[destination][source] = node;
        isSetMatrix[destination][source] = true;
    }
  }

  public void printMatrix() {
    for (int i = 0; i < numOfNodes; i++) {
        for (int j = 0; j < numOfNodes; j++) {
            // We only want to print the values of those positions that have been marked as set
            if (isSetMatrix[i][j])
                System.out.format("%.2f\t", matrix[i][j].getWeight());
            else System.out.format("%8s", "/  ");
        }
        System.out.println();
    }
  }

  public void printEdges() {
    for (int i = 0; i < numOfNodes; i++) {
        System.out.print("Node " + i + " is connected to: ");
        for (int j = 0; j < numOfNodes; j++) {
            if (isSetMatrix[i][j]) {
                System.out.print(j + " ");
            }
        }
        System.out.println();
    }
  }

  public boolean hasEdge(int source, int destination) {
    return isSetMatrix[source][destination];
  }

  public Node getEdgeValue(int source, int destination) {
    if (!weighted || !isSetMatrix[source][destination])
        return null;
    return matrix[source][destination];
  }


    public static void main(String[] args) {

        // String line = "";  
        // String splitBy = ",";  
        // ArrayList<String[]> test = new ArrayList<>();
        // try {  
        //     //parsing a CSV file into BufferedReader class constructor  
        //     BufferedReader br = new BufferedReader(new FileReader("result.csv"));  
        //     while ((line = br.readLine()) != null) {  
        //         String[] business = line.split(splitBy);    // use comma as separator  
        //         test.add(business);
        //     }
            
        //     br.close();
        // }   
        // catch (IOException e) {  
        //     e.printStackTrace();  
        // }  
  
        // // Graph(numOfNodes, directed, weighted)
        // Graph graph = new Graph(5, false, true); // 5:37am

        // for (int i = 1; i < test.size(); i++) {
        //   for (int j = i + 1; j < test.size() - 1; j++) {
        //       if (test.get(i)[3].equals(test.get(j)[3]) && test.get(i)[3].equals("\"Portland\"")) {
        //         double lat1 = Double.parseDouble(test.get(i)[6].replace("\"", ""));
        //         double lat2 = Double.parseDouble(test.get(j)[6].replace("\"", ""));
        //         double lon1 = Double.parseDouble(test.get(i)[7].replace("\"", ""));
        //         double lon2 = Double.parseDouble(test.get(j)[7].replace("\"", ""));
        //         graph.addEdge(i, j, distance(lat1, lat2, lon1, lon2));
        //      }
            
        //   }
        // }
  
        // graph.addEdge(0, 2, 19);
        // graph.addEdge(0, 3, -2);
        // // graph.addEdge(1, 2, 3);
  
        // graph.printMatrix();
  
        // System.out.println();
        // System.out.println();
  
        // graph.printEdges();
  
        // System.out.println();
        // System.out.println("Does an edge from 1 to 0 exist?");
        // if (graph.hasEdge(0,1)) {
        //     System.out.println("Yes");
        // }
        // else System.out.println("No");
    }
  
}

