package com.mycompany.it22069;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class It22069 {

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
    
    public static Actor findActor(ArrayList<Show> showsList, ArrayList<Actor> actorsList, String firstName, String lastName) {
        float avgRate, maxRate = -1, minRate = 11;
        String maxRateTitle = "-", minRateTitle = "-";

        // Iterate over the actors list
        for (Actor actor : actorsList) {
            // Check if the first name and last name match
            if (actor.getFirstName().toLowerCase().equals(firstName.toLowerCase()) && actor.getLastName().toLowerCase().equals(lastName.toLowerCase())) {
                System.out.println("id: " + actor.getId() + "\nFirst Name: " + actor.getFirstName() + "\nLast name: " + actor.getLastName() + "\nShows:\n");

                // Get the shows associated with the actor
                ArrayList<String> shows = actor.getShows();
                for (String showId : shows) {
                    // Search for the show based on the show ID and retrieve the show object
                    Show show = searchShow(showsList, showId, 2);
                    System.out.println(show.getTitle() + "\n");

                    // Calculate the average rate for the show and update the maximum and minimum rates
                    avgRate = show.avgRate();
                    if (avgRate > maxRate) {
                        maxRate = avgRate;
                        maxRateTitle = show.getTitle();
                    }
                    if (avgRate < minRate) {
                        minRate = avgRate;
                        minRateTitle = show.getTitle();
                    }

                }

                // Ask the user if they want to see the best and worst rated titles
                System.out.print("Do you want to see the best and the worst Rated titles?(Y/N): ");
                String choice = scanString();
                if (choice.equals("Y")) {
                    if(maxRate==-1 && minRate==11){
                        maxRate=0;
                        minRate=0;
                    }
                    System.out.println("Best rated: \nTitle: " + maxRateTitle + "\nRate: " + maxRate);
                    System.out.println("Worst rated: \nTitle: " + minRateTitle + "\nRate: " + minRate);
                }

                return actor;
            }
        }
        return null;
    }

    public static Director findDirector(ArrayList<Show> showsList, ArrayList<Director> directorsList, String firstName, String lastName) {
        float avgRate, maxRate = -1, minRate = 11;
        String maxRateTitle = "-", minRateTitle = "-";

        // Iterate over the directors list
        for (Director director : directorsList) {
            // Check if the first name and last name match
            if (director.getFirstName().toLowerCase().equals(firstName.toLowerCase()) && director.getLastName().toLowerCase().equals(lastName.toLowerCase())) {
                System.out.println("id: " + director.getId() + "\nFirst Name: " + director.getFirstName() + "\nLast name: " + director.getLastName() + "\nShows:\n");

                // Get the shows associated with the director
                ArrayList<String> shows = director.getShows();
                for (String showId : shows) {
                    // Search for the show based on the show ID and retrieve the show object
                    Show show = searchShow(showsList, showId, 2);
                    System.out.println(show.getTitle() + "\n");

                    // Calculate the average rate for the show and update the maximum and minimum rates
                    avgRate = show.avgRate();
                    if (avgRate > maxRate) {
                        maxRate = avgRate;
                        maxRateTitle = show.getTitle();
                    }
                    if (avgRate < minRate) {
                        minRate = avgRate;
                        minRateTitle = show.getTitle();
                    }
                }

                // Ask the user if they want to see the best and worst rated titles
                System.out.print("Do you want to see the best and the worst Rated titles?(Y/N): ");
                String choice = scanString();
                if (choice.equals("Y")) {
                    if(maxRate==-1 && minRate==11){
                        maxRate=0;
                        minRate=0;
                    }
                    System.out.println("Best rated: \nTitle: " + maxRateTitle + "\nRate: " + maxRate);
                    System.out.println("Worst rated: \nTitle: " + minRateTitle + "\nRate: " + minRate);
                }
                return director;
            }
        }

        // Return null if the director is not found
        return null;
    }

    public static void findActorDirector(ArrayList<Show> showsList, ArrayList<Actor> actorsList, ArrayList<Director> directorsList, ArrayList<User> usersList) {
        // Prompt the user to choose between searching for an actor or director
        System.out.println("1:Search Actor\n2:Search director");
        int choice = scanInt();

        if (choice==1) {
            // Search for an actor
            System.out.print("Give Actors first name: ");
            String firstName = scanString();

            System.out.print("Give Actors last name: ");
            String lastName = scanString();

            // Call the findActor method to search for the actor
            Actor actor = findActor(showsList, actorsList, firstName, lastName);
            System.out.print("Do you want to add this Actor to your Favorites?(Y/N): ");

            String yon = scanString();
            if (yon.equals("Y")) {
                // Prompt the user to log in
                User user = loginUser(usersList);
                // Add the actor to the user's favorites
                user.addToFavorites(actor);
            }
        } else if (choice==2) {
            // Search for a director
            System.out.print("Give Directors first name: ");
            String firstName = scanString();

            System.out.print("Give Directors last name: ");
            String lastName = scanString();

            // Call the findDirector method to search for the director
            Director director = findDirector(showsList, directorsList, firstName, lastName);
            System.out.print("Do you want to add this Director to your Favorites?(Y/N): ");

            String yon = scanString();
            if (yon.equals("Y")) {
                // Prompt the user to log in
                User user = loginUser(usersList);
                // Add the director to the user's favorites
                user.addToFavorites(director);
            }
        }

    }

    public static void addShow(ArrayList<Show> showsList, ArrayList<Actor> actorsList, ArrayList<Director> directorsList) {
        // Create a new show using the createNewShow method
        Show tmp = Show.createNewShow(actorsList, directorsList);

        // Add the new show to the showsList
        showsList.add(tmp);

        // Check the type of the show and display the corresponding information
        if (tmp instanceof Movie) {
            // If the show is a Movie
            System.out.println("Type : Movie\n" + tmp.display());
        } else if (tmp instanceof Series) {
            // If the show is a Series
            System.out.println("Type : Series\n" + tmp.display());
        } else if (tmp instanceof MiniSeries) {
            // If the show is a MiniSeries
            System.out.println("Type : MiniSeries\n" + tmp.display());
        }
    }

    public static User signUpUser(ArrayList<User> usersList) {

        System.out.print("Give First Name: ");
        String firstName = scanString();

        System.out.print("Give Last Name: ");
        String lastName = scanString();

        System.out.print("Give username: ");
        String username = scanString();

        System.out.print("Give Email: ");
        String email = scanString();

        System.out.print("Give Password: ");
        String password = scanString();

        // Create a new User object using the provided information
        User user = new User(firstName, lastName, username, email, password);

        // Add the new user to the usersList
        usersList.add(user);

        // Return the newly created user
        return user;
    }

    public static User loginUser(ArrayList<User> usersList) {

        // Prompt the user to choose login or signup
        System.out.println("1:Login");
        System.out.println("2:Signup");
        int choice = scanInt();
        User users = null;
        if (choice == 2) {
            // If the user chooses signup, call the signUpUser method to create a new user
            users = signUpUser(usersList);
            return users;
        }

        System.out.print("Give email: ");
        String email = scanString();

        System.out.print("Give password: ");
        String password = scanString();

        // Iterate through the usersList to find a matching user with the provided email and password
        for (User user : usersList) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return user;
            }
        }

        // If no matching user is found, return null
        return users;
    }

    public static void findShow(ArrayList<Show> showsList, ArrayList<User> usersList) {
    
        System.out.println("\n1:Search by Title\n2:Search by Id\n3:Search by last Air-Date");
        int choice = scanInt();
        String line = null;

        // Prompt the user for search options
        switch (choice) {
            case 1:
                System.out.print("Give Title : ");
                break;
            case 2:
                System.out.print("Give Id : ");
                break;
            case 3:
                System.out.print("Give Air-Date : ");
                break;
        }

        line = scanString();

        // Search for the show based on user input
        Show show = searchShow(showsList, line, choice);
        if (show != null) {
            // Display show details
            show.display();

            if (show instanceof Series) {
                // Display series-specific information
                Series series = (Series) show;
                System.out.println("\nlast Air-Date: " + series.getAirDate() + "\nSeasons: " + series.getSeason());
            }
            User user = loginUser(usersList);
            if (user != null) {
                System.out.print("1:Add Review \n2:See all Reviews\n");
                choice = scanInt();
                if (choice == 1) {
                    // Add a review for the show
                    show.addReview(user.getId(), user.getUsername());
                } else if (choice == 2) {
                    // Display all reviews for the show
                    show.seeReviews();
                }
            }
        }
    }

    public static Show searchShow(ArrayList<Show> showsList, String line, int choice) {

        Show showToUpdate = null;

        // Iterate through the list of shows
        for (Show show : showsList) {
            if (choice == 1) {
                if (show.getTitle().toLowerCase().equals(line.toLowerCase())) {
                    showToUpdate = show;
                }
            } else if (choice == 2) {
                if (show.getId().toLowerCase().equals(line.toLowerCase())) {
                    showToUpdate = show;
                }
            } else if (choice == 3) {
                if (show.getAirDate().toLowerCase().equals(line.toLowerCase())) {
                    showToUpdate = show;
                }
            }
        }
        return showToUpdate;
    }

    public static void updateShow(ArrayList<Show> showsList, ArrayList<Actor> actorsList, ArrayList<Director> directorsList) {
        // Prompt for the search criteria
        int choice = scanInt();
        String line = null;

        switch (choice) {
            case 1:
                System.out.print("Give Title : ");
                break;
            case 2:
                System.out.print("Give Id : ");
                break;
            case 3:
                System.out.print("Give Air-Date : ");
                break;
        }

        line = scanString();
        Show showToUpdate = searchShow(showsList, line, choice);

        // Prompt for the update options
        System.out.print("\n1:Change No of Seasons\n"
                + "2:Change No of Episodes\n"
                + "3:Change Air Date\n"
                + "4:Add Actor");

        choice = scanInt();

        // Perform the selected update action
        switch (choice) {
            case 1: {
                // Change Number of Seasons
                if (showToUpdate instanceof Series) {
                    System.out.print("Give new Number Of Seasons: ");
                    int newSeasonNo = scanInt();
                    Series series = (Series) showToUpdate;
                    series.setSeason(newSeasonNo);
                } else {
                    System.out.println("This show has no seasons");
                }
                break;
            }
            case 2: {
                // Change Number of Episodes
                if (showToUpdate instanceof Series) {

                    System.out.print("Give the season Number : ");
                    int seasonNo = scanInt();

                    Series series = (Series) showToUpdate;
                    if (series.getSeason() >= seasonNo) {
                        System.out.print("\nGive number of Episodes: ");
                        int episodes = scanInt();

                        series.setEpisodes(seasonNo - 1, episodes);
                    } else {
                        System.out.println("Wrong Season Number");
                    }
                } else {
                    System.out.println("This show has no seasons");
                }
                break;
            }
            case 3: {
                // Change Air Date
                System.out.print("Give new last air-date: ");
                String newAirDate = scanString();
                showToUpdate.setAirDate(newAirDate);
                break;
            }
            case 4: {
                // Add Actor
                showToUpdate.addNewActor(actorsList);
                break;
            }

        }
    }

    // Displays the details of all the shows in the provided list.
    public static void display(ArrayList<Show> showsList) {
        for (Show show : showsList) {
            System.out.println(show.toString());
        }
    }

    // Displays the reviews written by the logged-in user for each show in the provided list.
    public static void myReviews(ArrayList<Show> showsList, ArrayList<User> usersList) {
        User user = loginUser(usersList);
        for (Show show : showsList) {
            for (Review review : show.getReviews()) {
                if (review.getUserId().equals(user.getId())) {
                    System.out.println(review.toString());
                }
            }
        }
    }

    // Displays the favorite shows of the logged-in user.
    public static void seeFavorites(ArrayList<User> usersList) {
        User user = loginUser(usersList);
        user.showFavorites();
    }

    public static void staticData(ArrayList<Show> showsList, ArrayList<Actor> actorsList, ArrayList<Director> directorsList) {

        Actor actor1 = new Actor("Leonardo", "DiCaprio", "USA", "-");
        Actor actor2 = new Actor("Meryl", "Streep", "USA", "-");
        Actor actor3 = new Actor("Brad", "Pitt", "USA", "-");
        Actor actor4 = new Actor("Tom", "Hanks", "USA", "-");
        Actor actor5 = new Actor("Emma", "Stone", "USA", "-");

        Director director1 = new Director("Christopher", "Nolan", "UK", "-");
        Director director2 = new Director("James", "Cameron", "USA", "-");
        Director director3 = new Director("Steven", "Spielberg", "USA", "-");
        Director director4 = new Director("Martin", "Scorsese", "USA", "-");
        Director director5 = new Director("Quentin", "Tarantino", "USA", "-");

        directorsList.add(director1);
        directorsList.add(director2);
        directorsList.add(director3);
        directorsList.add(director4);
        directorsList.add(director5);

        actorsList.add(actor1);
        actorsList.add(actor2);
        actorsList.add(actor3);
        actorsList.add(actor4);
        actorsList.add(actor5);

        showsList.add(new Movie("Inception", "July 16, 2010", new ArrayList<>(Arrays.asList("Action", "Adventure", "Sci-Fi")), "USA", director1, new ArrayList<Actor>(Arrays.asList(actor1, actor2, actor3))));
        showsList.add(new Movie("The Shawshank Redemption", "September 23, 1994", new ArrayList<>(Arrays.asList("Drama", "Crime")), "USA", director2, new ArrayList<Actor>(Arrays.asList(actor4, actor5, actor2))));
        showsList.add(new Movie("Pulp Fiction", "October 14, 1994", new ArrayList<>(Arrays.asList("Crime", "Drama")), "USA", director3, new ArrayList<Actor>(Arrays.asList(actor1, actor2, actor3))));
        showsList.add(new Movie("The Dark Knight", "July 18, 2008", new ArrayList<>(Arrays.asList("Action", "Crime", "Drama")), "USA", director1, new ArrayList<Actor>(Arrays.asList(actor2, actor4, actor5))));
        showsList.add(new Movie("The Lord of the Rings: The Fellowship of the Ring", "December 19, 2001", new ArrayList<>(Arrays.asList("Adventure", "Drama", "Fantasy")), "USA", director4, new ArrayList<Actor>(Arrays.asList(actor2, actor3, actor4))));

        showsList.add(new Series("Stranger Things", "July 15, 2016", new ArrayList<>(Arrays.asList("Drama", "Fantasy", "Horror")), "USA", director1, new ArrayList<Actor>(Arrays.asList(actor1, actor2, actor3)), 4, new ArrayList<>(Arrays.asList(8, 9, 8, 9, 8, 8, 9))));
        showsList.add(new Series("Breaking Bad", "January 20, 2008", new ArrayList<>(Arrays.asList("Crime", "Drama", "Thriller")), "USA", director2, new ArrayList<Actor>(Arrays.asList(actor4, actor5, actor1)), 5, new ArrayList<>(Arrays.asList(7, 8, 9, 9, 10, 8, 8, 9))));
        showsList.add(new Series("Game of Thrones", "April 17, 2011", new ArrayList<>(Arrays.asList("Action", "Adventure", "Drama")), "USA", director3, new ArrayList<Actor>(Arrays.asList(actor2, actor4, actor5)), 8, new ArrayList<>(Arrays.asList(8, 8, 7, 9, 10, 9, 8, 7, 9, 8))));
        showsList.add(new Series("Friends", "September 22, 1994", new ArrayList<>(Arrays.asList("Comedy", "Romance")), "USA", director4, new ArrayList<Actor>(Arrays.asList(actor3, actor2, actor1)), 10, new ArrayList<>(Arrays.asList(8, 8, 9, 9, 10, 9, 9, 8, 9, 8, 9, 9, 8, 8, 9))));
        showsList.add(new Series("The Office", "March 24, 2005", new ArrayList<>(Arrays.asList("Comedy")), "USA", director5, new ArrayList<Actor>(Arrays.asList(actor1, actor5, actor3)), 9, new ArrayList<>(Arrays.asList(8, 9, 9, 8, 8, 8, 9, 8, 9, 9, 8, 8, 8))));

        showsList.add(new MiniSeries("Chernobyl", "May 6, 2019", new ArrayList<>(Arrays.asList("Drama", "History", "Thriller")), "USA", director1, new ArrayList<Actor>(Arrays.asList(actor1, actor2, actor3)), 5));
        showsList.add(new MiniSeries("The Queen's Gambit", "October 23, 2020", new ArrayList<>(Arrays.asList("Drama")), "USA", director2, new ArrayList<Actor>(Arrays.asList(actor4, actor5)), 7));
        showsList.add(new MiniSeries("Band of Brothers", "September 9, 2001", new ArrayList<>(Arrays.asList("Action", "Drama", "History")), "USA", director3, new ArrayList<Actor>(Arrays.asList(actor4, actor5, actor1)), 10));
        showsList.add(new MiniSeries("Mare of Easttown", "April 18, 2021", new ArrayList<>(Arrays.asList("Crime", "Drama", "Mystery")), "USA", director4, new ArrayList<Actor>(Arrays.asList(actor2, actor1)), 7));
        showsList.add(new MiniSeries("The Night Manager", "February 21, 2016", new ArrayList<>(Arrays.asList("Action", "Drama", "Thriller")), "USA", director5, new ArrayList<Actor>(Arrays.asList(actor3, actor4, actor5)), 6));
    }

    public static void main(String[] args) {

        ArrayList<Show> showsList = new ArrayList();
        ArrayList<Actor> actorsList = new ArrayList();
        ArrayList<Director> directorsList = new ArrayList();
        ArrayList<User> usersList = new ArrayList();

        Scanner scanner = new Scanner(System.in);

        staticData(showsList, actorsList, directorsList);

        do {
            System.out.print("\n1:Add new Show\n");
            System.out.print("2:Update Show\n");
            System.out.print("3:Find Show\n");
            System.out.print("4:See my Reviews\n");
            System.out.print("5:Find Actor/Director \n");
            System.out.print("6:See my Favorite actors and directors \n");
            System.out.print("7:Exit\n\n");
            System.out.print("choice: ");

            int line = scanInt();
            if (line==1) {
                // Add a new show
                addShow(showsList, actorsList, directorsList);
            } else if (line==2) {
                // Update a show
                updateShow(showsList, actorsList, directorsList);
                display(showsList);
            } else if (line==3) {
                // Find a show
                findShow(showsList, usersList);
            } else if (line==4) {
                // See the reviews written by the user
                myReviews(showsList, usersList);
            } else if (line==5) {
                // Find an actor or director
                findActorDirector(showsList, actorsList, directorsList, usersList);
            } else if (line==6) {
                // See favorite actors and directors
                seeFavorites(usersList);
            } else if (line==7) {
                // Exit the program
                break;
            }
        } while (true);

        System.out.println("Finished");
    }

}
