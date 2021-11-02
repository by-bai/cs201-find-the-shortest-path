package cs201g2t6.model;

public class priQueue implements Comparable<priQueue>{
    private Business biz;
    private double distance;

    public priQueue(Business biz, double distance){
        this.biz = biz;
        this.distance = distance;
    }
    
    
    public Business getBiz(){
        return biz;
    }

    public double getDistance() {
        return distance;
    }


    @Override
    public int compareTo(priQueue o) {
        if (this == o){
            return 0;
        }
        if(this.distance < o.distance){
            return 1;
        }
        return -1;
    }
    
    
}
