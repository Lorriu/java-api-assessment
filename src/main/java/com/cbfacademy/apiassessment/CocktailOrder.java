package com.cbfacademy.apiassessment;

import java.util.Scanner;

public class CocktailOrder extends Cocktail {

  String userCocktailName;


  public CocktailOrder(String userCocktailName, String alcohol){


     // Prompt user for input on cocktail name & alcohol amount 
      Scanner scanner = new Scanner(System.in);

      System.out.print("Enter the name of the cocktail you want to order: ");
      String userCocktailName = scanner.nextLine();



    //choose cocktail name
    this.userCocktailName = userCocktailName;


    //Print out order name and price
    System.out.println("You have ordered a " + userCocktailName + "for $" + calculatePrice() + ". Thank you!");

  }

  // Function to get alcohol amount from user input
      private static String getAlcoholAmount(Scanner scanner) {
        System.out.print("Select alcohol amount ('None', 'Double', or 'Regular'): ");
        String alcoholAmount = scanner.nextLine();

       // ADD ERROR HANDLING!! 
        return alcoholAmount;
      }


    //calculate price of cocktail based on alcohol content
    public double calculatePrice() {

        double cocktailCost;

             //switch statement to decide price
        switch (alcoholAmount){
          //no alcohol/mocktail price  
          case "None": cocktailCost = 10.00;
            break;
          //double shot of alcohol price  
          case "Double": cocktailCost = 17.75;
            break;
          //regular cocktail price  
          default: cocktailCost = 12.50;
    
        }
        
        return cocktailCost;
         }



      public String getAlcohol() {
          return alcohol;
      }
  
      public void setAlcohol(String alcohol) {
          this.alcohol = alcohol;
      }
  
      public String getName() {
          return userCocktailName;
      }
  
      public void setName(String userCocktailName) {
          this.userCocktailName = userCocktailName;
      }
    
}
