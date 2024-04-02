package com.mycompany.it22069;

import java.util.ArrayList;


public class MiniSeries extends Show {
    private int episodes;

    // Setter for the number of episodes in the mini-series
    public void setEpisodes(int noOfEpisodes){
        this.episodes = noOfEpisodes;
    }

    // Constructor
    public MiniSeries(String title, String airDate, ArrayList<String> category, String coutnry, Director director,ArrayList<Actor> actors,int episodes) {
        // Call the constructor of the parent class (Show) using the "super" keyword
        super(title, airDate, category, coutnry, director, actors);
        
        // Initialize the additional attribute specific to the MiniSeries class
        this.episodes = episodes;
    }

    // Override the toString() method from the parent class (Show)
    @Override
    public String toString() {
        return "Episodes: "+this.episodes;
    } 
}
