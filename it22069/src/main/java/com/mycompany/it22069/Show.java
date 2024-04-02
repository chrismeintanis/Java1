package com.mycompany.it22069;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Show {

    // Static variable to track the count of shows
    private static int count = 0;

    // Instance variables
    private String id;
    private String title;
    private String airDate;
    private ArrayList<String> category;
    private String coutnry;
    private Director director;
    private ArrayList<Actor> actors;
    private ArrayList<Review> reviews;

    
    public static int scanInt(){
        Scanner scanner = new Scanner(System.in);
        boolean validInput=false;
        int number=-1;
        while (!validInput) {
            try {
                number = scanner.nextInt();
                scanner.nextLine();
                validInput = true; // Input is valid, exit the loop
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter an integer.");
                scanner.nextLine(); // Clear the input buffer
            }
        }
        return number;
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
    
    
    // Check if a director with the given first name and last name exists in the list of directors
    public static Director directorExists(String firstName, String lastName, ArrayList<Director> directorsList) {
        for (Director drt : directorsList) {
            if (drt.getFirstName().toLowerCase().equals(firstName) && drt.getLastName().toLowerCase().equals(lastName)) {
                return drt;
            }
        }
        return null;
    }

    // Create a new show by taking user input
    public static Show createNewShow(ArrayList<Actor> actorsList, ArrayList<Director> directorsList) {
        System.out.println("1:Movie\n2:Series\n3:Mini Series");
        int choice = scanInt();

        // Read in values for each field
        System.out.print("Enter title: ");
        String title = scanString();

        System.out.print("Enter air date: ");
        String airDate = scanString();

        System.out.print("Enter categories (comma-separated): ");
        String categoriesInput = scanString();
        ArrayList<String> categories = new ArrayList<>(Arrays.asList(categoriesInput.split(",")));

        System.out.print("Enter country: ");
        String country = scanString();

        int numberOfSeasons = 0;
        int numberOfEpisodes = 0;
        int episodes = 0;
        ArrayList<Integer> episodesList = new ArrayList();

        // Handle specific fields based on the show type
        switch (choice) {
            case 2 -> {
                System.out.print("Enter number of Seasons: ");
                numberOfSeasons = scanInt();
                System.out.println("Give number of Episodes");
                for (int i = 0; i < numberOfSeasons; i++) {
                    System.out.print("Season " + (i + 1) + " :");
                    numberOfEpisodes = scanInt();
                    episodesList.add(numberOfEpisodes);
                }
                break;
            }
            case 3 -> {
                System.out.print("Give number of Episodes: ");
                episodes = scanInt();
                break;
            }
        }

        System.out.print("Enter director First name: ");
        String directorFirstName = scanString();

        System.out.print("Enter director Last name: ");
        String directorLastName = scanString();

        Director director = directorExists(directorFirstName.toLowerCase(), directorLastName.toLowerCase(), directorsList);

        // Check if director exists, otherwise prompt for director details
        if (director == null) {
            System.out.print("Enter director country: ");
            String directorCountry = scanString();

            System.out.print("Enter director website: ");
            String directorWebsite = scanString();
            director = new Director(directorFirstName, directorLastName, directorCountry, directorWebsite);
        }

        System.out.print("Enter number of actors(max 10): ");
        int numActors = scanInt();

        ArrayList<Actor> actors = new ArrayList();
        // Read in values for each actor
        for (int i = 0; i < numActors; i++) {
            Actor actor = Actor.newActor(actorsList);
            actors.add(actor);
        }

        // Create and return a new Show object based on the user's input
        switch (choice) {
            case 1:
                return new Movie(title, airDate, categories, country, director, actors);
            case 2:
                return new Series(title, airDate, categories, country, director, actors, numberOfSeasons, episodesList);
            case 3:
                return new MiniSeries(title, airDate, categories, country, director, actors, episodes);
        }

        // Default to creating a generic Show object if the choice is invalid
        return new Show(title, airDate, categories, country, director, actors);
    }

    // Add a new actor to the list of actors for the show
    public void addNewActor(ArrayList<Actor> actorsList) {
        Actor actor = Actor.newActor(actorsList);
        this.actors.add(actor);

    }

    // Override toString() method to display show details
    @Override
    public String toString() {
        String s = "\n" + "id=" + this.id + "\ntitle=" + this.title + "\nairDate=" + this.airDate
                + "\ncategory=" + this.category + "\ncoutnry=" + this.coutnry + "\ndirector:" + this.director.toString()
                + "\nactors:";
        for (Actor tmpAct : actors) {
            s += tmpAct.toString();
        }
        return s;
    }

    // Display basic show information
    public String display() {
        return ("Id : " + this.id + "\nTitle : " + this.title + "\nCategory : " + this.category + "\nDirector : " + this.director.getFirstName() + " " + this.director.getLastName());
    }

    // Add a review for the show
    public void addReview(String userId, String username) {
        Review review = Review.createReview(userId, username);
        this.reviews.add(review);
    }

    // Display all reviews for the show
    public void seeReviews() {
        for (Review review : reviews) {
            System.out.println(review.toString());
        }
    }

    // Calculate the average rating for the show based on its reviews
    public float avgRate() {
        float sum = 0;
        int i = 0;
        for (Review review : this.reviews) {
            sum += review.getRate();
            i++;
        }
        return (float) sum / i;
    }

    // Constructors
    public Show(String title, String airDate, ArrayList<String> category, String coutnry, Director director, ArrayList<Actor> actors) {
        count++;
        this.id = "S" + count;
        this.title = title;
        this.airDate = airDate;
        this.category = category;
        this.coutnry = coutnry;
        this.director = director;
        this.director.addShow(this.id);
        this.actors = actors;
        this.reviews = new ArrayList();
        int i = 0;
        for (Actor act : actors) {
            act.addShow(this.id);
        }
    }

    // Getters and Setters
    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAirDate() {
        return airDate;
    }

    public void setAirDate(String airDate) {
        this.airDate = airDate;
    }

    public ArrayList<String> getCategory() {
        return category;
    }

    public void setCategory(ArrayList<String> category) {
        this.category = category;
    }

    public String getCoutnry() {
        return coutnry;
    }

    public void setCoutnry(String coutnry) {
        this.coutnry = coutnry;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

}

