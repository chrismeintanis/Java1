package com.mycompany.it22069;
import java.util.Scanner;


public class Review {
    private int rate;
    private String username;
    private String userId;

    @Override
    public String toString() {
        return "\nReview\n" + "\nusername=" + this.username + "\nuserId=" + this.userId + "\nrate=" + this.rate + "\n";
    }
 
    public static Review createReview(String userId,String username){
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Give Rate(1-10): ");
        int rate = scanner.nextInt();
        scanner.nextLine();
        
        return(new Review(rate,username,userId));
    }
    
    public Review(int rate,String username,String userId){
        this.rate=rate;
        this.username=username;
        this.userId=userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public int getRate(){
        return this.rate;
    }
    
}

