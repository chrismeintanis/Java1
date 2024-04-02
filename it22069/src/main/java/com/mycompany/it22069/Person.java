package com.mycompany.it22069;

// Abstract class
abstract class Person {
    // Static variable to track the count of Person objects
    private static int count;
    // Instance variables
    private String id;
    private String firstName;
    private String lastName;
    private String country;
    
    // Constructor
    public Person(String firstName, String lastName, String country) {
         // Generate a unique id for each Person object using the count
        this.id = "P" + count;
        count++;
        // Initialize the instance variables
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
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

    // Getter for country
    public String getCountry() {
        return country;
    }
    
    // Setter for country
    public void setCountry(String country) {
        this.country = country;
    }
    
    // Override the toString() method
    @Override
    public String toString() {
        return  "\nid=" + id + "\nfirstName=" + firstName + "\nlastName=" + lastName + "\ncountry=" + country ;
    }
}
