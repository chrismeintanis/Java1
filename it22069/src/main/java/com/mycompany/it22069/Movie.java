package com.mycompany.it22069;
import java.util.ArrayList;

public class Movie extends Show{
    // Constructor
    public Movie(String title, String airDate, ArrayList<String> category, String coutnry, Director director, ArrayList<Actor> actors) {
        // Call the constructor of the parent class (Show) using the "super" keyword
        super(title, airDate, category, coutnry, director, actors);
    }    
    
    // Override the toString() method from the parent class (Show)
    @Override
    public String toString() {
        // Call the toString() method of the parent class using the "super" keyword
        return super.toString();
    }    
}
