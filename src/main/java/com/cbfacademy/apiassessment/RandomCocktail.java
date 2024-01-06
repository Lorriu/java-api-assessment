package com.cbfacademy.apiassessment;

import java.util.List;
import java.util.Random;

public class RandomCocktail {


    //select random cocktail
    public static Cocktail selectRandomCocktail(List<Cocktail> cocktails) {

        Random rand = new Random();

        int randomIndex = rand.nextInt(cocktails.size());

        return cocktails.get(randomIndex);
    }

    //select random alcohol based on strength parameter
    


    
}
