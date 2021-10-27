package cs201g2t6.model;

import java.security.KeyStore.Entry;
import java.util.*;
import cs201g2t6.utils.*;


public class DijkstraPQ {

    private Graph2 graph;
    private Business userLocation;
    private List<Business> nearbyBusinessList;
    
    //unique to this class
    private Map<Business, Double> totalCosts;
    private Map<Business, Business> prevBusiness; 
    private PriorityQueue<priQueue> minPQ;
    private Set<Business> visited; 


    public DijkstraPQ(Graph2 graph, Business userLocation){
        this.graph = graph;
        this.userLocation = userLocation; 
        nearbyBusinessList = graph.getBusinessList(); 

        // initializing variables unique to the class
        totalCosts = new HashMap<>();
        prevBusiness = new HashMap<>();
        // linked hashset preserves order
        visited = new LinkedHashSet<>(nearbyBusinessList.size());
        minPQ = new PriorityQueue<>(nearbyBusinessList.size(),new priQueueComparator());
        
        // first put all business in total cost , with distance as max 1000
        // do so, so that later easy to update costs, don't need to worry about adding in
            // first put startNode/ userLocation and its cost as 0
        totalCosts.put(userLocation, 0.0);
        for(Business biz : nearbyBusinessList){
            if(biz != userLocation){
                totalCosts.put(biz, 1000.0); 
            }
        }

        // first put startNode/ userLocation and its prev Business as itself
        prevBusiness.put(userLocation, null); 
        
        // add userlocation into the minimum priority queue
        priQueue entry = new priQueue(userLocation, 0.0); 
        minPQ.add(entry); 

    }

    public void doDijkstraPQ(){
        System.out.println( "size"+ nearbyBusinessList.size());
        int checker = 1;
        boolean set= true;
        
        while(set){
            priQueue smallestEntry = minPQ.poll(); // removes lowest
            visited.add(smallestEntry.getBiz());
            Business newSmallestBiz = smallestEntry.getBiz();
            List <Business> neighboursOfOneBiz = graph.getNeighboursOfBusiness(newSmallestBiz);
            for(Business bizNeighbour : neighboursOfOneBiz ){
                // if neighbour is not in visited set
                if(!visited.contains(bizNeighbour)){
                    // check if bizNeighbour is in priority queue
                    int check = 0;
                    for(priQueue entry: minPQ){
                        if(entry.getBiz().equals(bizNeighbour)){
                            check = 1;
                            break;

                        }
                    }
                    // get last entry in visited set
                    // smallest entry is last added to visited set
                    Business lastElement = newSmallestBiz;
                    double distance = 0.0;
                    // distance between neighbour - recently removed + recently removed - user location
                    distance = CalculateDistance.calculateDistanceInKilometer(lastElement.getLatitude(),
                            lastElement.getLongitude(), bizNeighbour.getLatitude(), bizNeighbour.getLongitude())
                            + totalCosts.get(lastElement);

                    // check if neighbour - lastElement in visited + lastElement in visited - user ( in total cost)
                    // is greater than neighbour -user as already written in total costs 
                    if ((check == 1 && (distance < totalCosts.get(bizNeighbour))) || check == 0) {
                        priQueue entry = new priQueue(bizNeighbour, distance);
                        // add entry to priority queue
                        minPQ.add(entry);
                        // add entry to total costs
                        totalCosts.put(bizNeighbour, distance);
                        // add entry to previous biz
                        prevBusiness.put(bizNeighbour, lastElement);

                    }



                }else{
                    // if neighbour is in visited set, dont do anything, move to next neighbour
                    continue;
                }
                
            }
            if (minPQ.isEmpty()) {
                set = false;

            } else {
                set = true;

            }
            
        }
        int check = 0;
            int i = 0;
            for (i = 0; i <= nearbyBusinessList.size() - 1; i++) {
                List<String> categories = nearbyBusinessList.get(i).getCategories();

                for (int j = 0; j <= categories.size() - 1; j++) {
                    if (categories.get(j).equals(" Restaurants")) {
                        check = 1;
                        break;
                    }

                }
                if (check == 1) {
                    break;
                }

            }
            String output = "";
            Business chosenBiz = null;
            if (check == 1) {
                chosenBiz = nearbyBusinessList.get(i);
                Business endpoint = prevBusiness.get(chosenBiz);
                output = chosenBiz.getName();
                System.out.println(chosenBiz.getName());
                System.out.println(endpoint.getName());
                while(endpoint != userLocation){
                    output = endpoint.getName() + "->" + output;
                    endpoint = prevBusiness.get(endpoint);
                // 1 -> 2 -> 3 -> 4
                }
                System.out.println("User Location -> " + output);
                System.out.println("Facts about the biz:");
                System.out.println("Ratings in stars" + chosenBiz.getStars());
                System.out.println(chosenBiz.getCategories().toString());
            

            }

                
            

    }
        
        
}

        



    



    
