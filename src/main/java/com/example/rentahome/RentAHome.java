/*Developed by Christian Zaccaria (20092351)
 * CA 1 - Rent-A-Home system
 */

package com.example.rentahome;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RentAHome {

   private ArrayList<Property> available;



    public RentAHome() { available = RentAHomeMain.available;}

    public int addProperty(String description, String address, String category, String cnty, String twn,
                           String BER, String eircode, String price, String propertyAgent)    {

        Property property = new Property(description,address,category, cnty, twn, BER, eircode, price, propertyAgent);

        if(available.add(property)){
            return Property.getPropertyId() - 1;
        }
        return -1;
    }

    //List all agents from the HashMap
    public String listAllAgents(){
        String allAgents = "All Agents:\n";
        for(Map.Entry<String, User> entry : RentAHomeMain.users.entrySet()){
            allAgents += entry.getKey() + " : " + entry.getValue().toString() + "\n";
        }
        return allAgents;
    }

    public String listAllProperties(){
        String allProperties = "All Properties\n";
        for(Property p:available){
            allProperties += "\nID: " + p.getId()+": " + "Description: " + p.getDescription() + "\nPrice range: " + p.getPrice() + "\nProperty Agent: " + p.getPropertyAgent() + "\n";
        }
        return allProperties;
    }






    public String listAllPropertiesSpecific(String county, String town, String category, String price){
        String allProperties ="Available Properties by search:\n";


        for(Property p:available) {
            if (p.getCounty().equals(county) && p.getTown().equals(town)
                    && p.getCategory().equals(category)
                    && p.getPrice().equals(price))
            {

                allProperties += p;

            }
            else {
                allProperties = "Nothing Available";
            }

        }

        return allProperties;
    }

    public Property getPropertyDetailsById(int id) {
        for (Property p : available) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public boolean editProperty( int id, String description, String address, String BER, String eircode, String propertyAgent,
                                String cnty, String twn, String category, String price) {

        for (int i = 0; i < available.size(); i++) {
            Property p = available.get(i);
            if (p.getId() == id) {
                p.setDescription(description);
                p.setAddress(address);
                p.setCategory(category);
                p.setCounty(cnty);
                p.setTown(twn);
                p.setBER(BER);
                p.setEircode(eircode);
                p.setPrice(price);
                p.setPropertyAgent(propertyAgent);
                return true;
            }
        }
        return false;  //No such id found
    }


    public boolean deleteProductByID(int id){
        for(int i=0; i< available.size(); i++){
            Property p = available.get(i);
            if(p.getId() == id){ //those this product match the id passed to the method?
                available.remove(i);
                return true;
            }
        }
        return false;  //No such id found

    }

    public void deleteAgent(String email) {
        RentAHomeMain.users.remove(email);
    }


}
