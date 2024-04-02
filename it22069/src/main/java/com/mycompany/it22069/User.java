package com.mycompany.it22069;
import java.util.ArrayList;


public class User {
    // Static variable to track the count of User objects
    private static int count;
    // Instance variables
    private String id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private ArrayList<Person> favorites;
    
    // Method to add a person to favorites
    public void addToFavorites(Person person){
        this.favorites.add(person);
    }
    
    // Method to show the favorite persons
    public void showFavorites(){
        for(Person person: favorites){
            if(person instanceof Actor){
                System.out.println("\nActor" + person.toString());
            }else if(person instanceof Director){
                System.out.println("\nDirector" + person.toString());
            }
        }
    }
    
    // Constructor
    public User(String firstName,String lastName,String username,String email,String password) {
        // Generate a unique id for each User object using the count
        this.id = "U" + count;
        count++;
        // Initialize the instance variables
        this.firstName=firstName;
        this.lastName=lastName;
        this.username=username;
        this.email=email;
        this.password=password;
        // Initialize the favorites ArrayList
        this.favorites=new ArrayList();
    }

    // Getter for id
    public String getId() {
        return id;
    }

    // Setter for id
    public void setId(String id) {
        this.id = id;
    }

    // Getter for firstName
    public String getFirstName() {
        return firstName;
    }

    // Setter for firstName
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // Getter for lastName
    public String getLastName() {
        return lastName;
    }

    // Setter for lastName
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // Getter for email
    public String getEmail() {
        return email;
    }

    // Setter for email
    public void setEmail(String email) {
        this.email = email;
    }

    // Getter for password
    public String getPassword() {
        return password;
    }

    // Setter for password
    public void setPassword(String password) {
        this.password = password;
    }

    // Getter for username
    public String getUsername() {
        return username;
    }

    // Setter for username
    public void setUsername(String username) {
        this.username = username;
    }
}
