package com.cbfacademy.apiassessment;

public class CocktailOrder {

  String alcohol;

  public CocktailOrder(String name, String alcohol){

    //alocate alcohol content

    //choose cocktail name

    //calculate price of cocktail
    calculatePrice();



  }




    //calculate price of cocktail based on alcohol content
    public double calculatePrice() {

        double cocktailCost;

             //switch statement to decide price
        switch (alcohol){
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
    
}
