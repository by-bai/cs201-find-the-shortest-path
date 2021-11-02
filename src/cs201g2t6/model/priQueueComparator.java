package cs201g2t6.model;

import java.util.Comparator;

public class priQueueComparator implements Comparator<priQueue>{

    @Override
    public int compare(priQueue o1, priQueue o2) {
        // TODO Auto-generated method stub
        if(o1.getDistance() < o2.getDistance()){
            return -1;
        }else if(o1.getDistance() > o2.getDistance()){
            return 1;
        }
        return 0;
    }

    
}
