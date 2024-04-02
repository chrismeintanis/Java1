package com.mycompany.it22069;
import java.util.ArrayList;

public class Series extends Show{
    private int noSeason;
    private ArrayList<Integer> episodes;

     // Setter for the number of seasons
    public void setSeason(int season){
        this.noSeason = season;
    }
    
    // Getter for the number of seasons
    public int getSeason(){
        return this.noSeason;
    }
    
    // Setter for the number of episodes in a season
    public void setEpisodes(int season,int noOfEpisodes){
        this.episodes.set(season, noOfEpisodes);
    }

    // Constructor
    public Series(String title, String airDate, ArrayList<String> category, String coutnry, Director director, ArrayList<Actor> actors,int Season, ArrayList<Integer> episodes) {
        // Call the constructor of the parent class (Show) using the "super" keyword
        super(title, airDate, category, coutnry, director, actors);
       
        // Initialize the additional attributes specific to the Series class
        this.noSeason = Season;
        this.episodes = episodes;
    }

    // Override the toString() method from the parent class (Show)
    @Override
    public String toString() {
        // Get the string representation of the parent class
        String tmp = super.toString();

        // Append information about each season and its episodes
        for(int i=0;i<this.noSeason;i++){
            tmp += "\nseason " + (i+1) + " has " + this.episodes.get(i) +" episodes";
        }
        
        // Return the final string representation
        return tmp;
    } 
}

