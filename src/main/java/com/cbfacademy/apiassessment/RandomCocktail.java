package com.cbfacademy.apiassessment;

import java.util.List;
import java.util.Random;

import com.cbfacademy.apiassessment.cocktailapi.src.main.java.com.cocktail.cocktailapi.Cocktail;

public class RandomCocktail {


    //select random cocktail
    public static Cocktail selectRandomCocktail(List<Cocktail> cocktails) {

        Random rand = new Random();

        int randomIndex = rand.nextInt(cocktails.size());

        return cocktails.get(randomIndex);
    }

    //select random alcohol based on strength parameter
    


    
}
