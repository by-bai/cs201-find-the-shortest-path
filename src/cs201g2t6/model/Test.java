package cs201g2t6.model;

import java.util.PriorityQueue;

public class Test {

    private PriorityQueue<priQueue> minPQ = new PriorityQueue<>();
    
    public void test(){
        Business user1 = new Business("aks", "aks", 40.0175444, -105.28335, null, 0);
        Business user2 = new Business("ken", "aks", 30.0175444, -195.28335, null, 0);
        Business user3 = new Business("ky", "aks", 30.0175444, -195.28335, null, 0);
        priQueue entry1 = new priQueue(user1, 0.0);
        priQueue entry2 = new priQueue(user2, 0.4);
        priQueue entry3 = new priQueue(user3, 0.4);
        minPQ.add(entry2);
        minPQ.add(entry1);
        minPQ.add(entry3);
        System.out.println();
        for (priQueue entry : minPQ) {
            System.out.println(entry.getBiz().getName());
        }

    }


    
}
