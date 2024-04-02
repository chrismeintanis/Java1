package com.mycompany.it22069;

import java.util.ArrayList;
import java.util.Scanner;


public class Actor extends Person{
    // Instance variables specific to actors
    private String website;
    private ArrayList<String> shows;

    public Actor(String firstName,String lastName,String country,String website) {
        // Call the constructor of the parent class (Person) to initialize common attributes
        super(firstName,lastName,country);
        // Initialize the instance variables specific to actors
        this.shows = new ArrayList();
        this.website = website;
    }
    
    //Method to add a show to the actor's list of shows.
    public void addShow(String showId){
        this.shows.add(showId);
    }
    
    //Overrides the toString() method to provide a string representation of the `Actor` object.
    @Override
    public String toString() {
        return super.toString()+ "\nwebsite=" + website;
    }
    
    //Getter for the list of shows the actor has appeared in.
    public ArrayList<String> getShows() {
        return shows;
    }
    
    //Static method to check if an actor with the given first name and last name already exists in the list of actors.
    public static Actor actorExists(String firstName,String lastName,ArrayList<Actor> actorsList){
        for(Actor act:actorsList){
            if(act.getFirstName().toLowerCase().equals(firstName) && act.getLastName().toLowerCase().equals(lastName)){
                return act;
            }
        }
        return null;
    }
    
    public static String scanString(){
        Scanner scanner = new Scanner(System.in);
        boolean validInput = false;
        String input = "";

        while (!validInput) {
            try {
                input = scanner.nextLine();
                validInput = true; // Input is valid, exit the loop
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a string.");
                scanner.nextLine(); // Clear the input buffer
            }
        }
        return input;
    }
    
    
    //Static method to create a new actor and add it to the list of actors.
    public static Actor newActor(ArrayList<Actor> actorsList){
        System.out.print("Enter actor First name: ");
        String actorFirstName = scanString();

        System.out.print("Enter actor Last name: ");
        String actorLastName = scanString();

        // Check if the actor already exists in the list
        Actor actor = Actor.actorExists(actorFirstName.toLowerCase(), actorLastName.toLowerCase(), actorsList);
        if (actor == null) {
            System.out.print("Enter actor country: ");
            String actorCountry = scanString();

            System.out.print("Enter actor website: ");
            String actorWebsite = scanString();
            // Create a new actor object and add it to the list of actors
            actor = new Actor(actorFirstName, actorLastName, actorCountry, actorWebsite);
            actorsList.add(actor);
        }
        return actor;
    }
}

