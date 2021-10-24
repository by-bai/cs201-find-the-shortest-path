package cs201g2t6.utils;

import java.io.*;
import java.util.*; 
import cs201g2t6.model.Business;

public class FileReader {
  
    public static List<Business> readFile(String filename) throws FileNotFoundException {

        List<Business> businessList = new ArrayList<>();

        Scanner fileSc = new Scanner(new FileInputStream(filename));
            fileSc.useDelimiter(",");

            while (fileSc.hasNext()) {
                String[] line = fileSc.nextLine().split(",");
                 String id = line[0];
                 String name = line[1];
                 double latitude = Double.parseDouble(line[2]);
                 double longitude = Double.parseDouble(line[3]); 
                 String[] categories = line[4].split("/");
                 List<String> categoriesList = new ArrayList<>(Arrays.asList(categories));  
                 double stars = Double.parseDouble(line[5]); 
                 Business business = new Business(id, name, latitude, longitude, categoriesList, stars);
                 businessList.add(business); 

            }

        return businessList;
    }
}
