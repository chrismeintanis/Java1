package com.mycompany.it22069;

import java.util.ArrayList;


public class Director extends Person{
    // Instance variables specific to directors
    private String website;
    private ArrayList<String> shows;
    
    //Constructor for creating a `Director` object
    public Director(String firstName,String lastName,String country,String website) {
        // Call the constructor of the parent class (Person) to initialize common attributes
        super(firstName,lastName,country);
        // Initialize the instance variables specific to directors
        this.shows = new ArrayList();
        this.website = website;
    }

    //Method to add a show to the director's list of shows.
    public void addShow(String showId){
        this.shows.add(showId);
    }

    //Getter for the list of shows the director has directed.
    public ArrayList<String> getShows() {
        return shows;
    }
    
    
    //Overrides the toString() method to provide a string representation of the `Director` object.
    @Override
    public String toString() {
        return super.toString()+"\nwebsite=" + website ;
    }

    //Getter for the website of the director.
    public String getWebsite() {
        return website;
    }

    //Setter for the website of the director.
    public void setWebsite(String website) {
        this.website = website;
    }
}
