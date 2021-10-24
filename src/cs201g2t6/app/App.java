package cs201g2t6.app;

import cs201g2t6.utils.*;
import cs201g2t6.model.Business;
import java.util.*;
import java.io.FileNotFoundException; 

public class App {
    public static void main(String[] args) {
        
        try {
            List<Business> businessList = FileReader.readFile("data/businesstest.csv");
            FilterRestaurants.getRestaurantsOnly(businessList); 
            FindBusinessesNearby.getBusinessesNearby(businessList); 
            
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist!"); 
        } 
       
       
    }
}