package com.cbfacademy.apiassessment;

import java.util.ArrayList;
import java.util.List;

public class FreshFruit {

    //ALGORITHIM
    //create a method to creeate a list of drink with fresh fruit
    public static List<Cocktail> hasFreshFruitCocktails(List<Cocktail> cocktails) {

        List<Cocktail> cocktailsWithFreshFruit = new ArrayList<>();

        for (Cocktail cocktail : cocktails) {
            // Check if fresh fruit is used in the cocktail from boolean
            if (cocktail.hasFreshFruit()) {
                cocktailsWithFreshFruit.add(cocktail);
            }
        }

        //returns list of cocktail
        return cocktailsWithFreshFruit;
    }

    
    //create a method to create a list of cocktails without fresh fruit
    public static List<Cocktail> hasNoFreshFruitCocktails(List<Cocktail> cocktails) {
        
        List<Cocktail> cocktailsWithoutFreshFruit = new ArrayList<>();

        for (Cocktail cocktail : cocktails) {

            // Check if the cocktail does not contain fresh fruit
            if (!cocktail.hasFreshFruit()) {
                cocktailsWithoutFreshFruit.add(cocktail);
            }
        }

        return cocktailsWithoutFreshFruit;
    }
    
}
