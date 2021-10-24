package cs201g2t6.utils;

import java.util.*; 
import cs201g2t6.model.Business;

public class FindBusinessesNearby {
    /* Find businesses that fall within the radius of user's location */

    public FindBusinessesNearby() {

    }
    
    //should accept user location as params too 
    public static List<Integer> getBusinessesNearby(List<Business> businessList) {
        List<Integer> distanceList = new ArrayList<>();
        Double[] userLocation = {40.0175444, -105.28335};

        for (int i = 0; i < businessList.size(); i++) {
            Business business = businessList.get(i); 
            int distanceBetweenLocations = CalculateDistance.calculateDistanceInKilometer(userLocation[0], 
                                                           userLocation[1], 
                                                           business.getLatitude(), 
                                                           business.getLongitude());
            distanceList.add(distanceBetweenLocations);

        }
        
        System.out.println(distanceList);
        return distanceList; 
     

    }

}
