package com.cbfacademy.apiassessment.cocktailapi;

import java.util.List;
import java.util.UUID;

import com.cbfacademy.apiassessment.cocktailapi.core.Repository;

public interface CocktailRepository extends Repository<Cocktail, UUID> {


    /**
     * Searches for Cocktails where the cocktail name matches the provided string.
     *
     * @param name the name of the cocktail
     * @return a list of cocktails that match the cocktail name
     */
     List<Cocktail> searchByCocktailName(String name);


      /**
     * Searches for Cocktails where the Cocktail strength matches the provided string.
     *
     * @param strength the name of the strength
     * @return a list of cocktails that match the strength name
     */
    List<Cocktail> searchByAlcoholStrength(String strength);


      /**
     * Searches for random Cocktail
     *
     * @param cocktails 
     * @return a random cocktail
     */
    Cocktail selectRandomCocktail(List<Cocktail> cocktails);

      /**
     * Searches for Cocktail by price
     *
     * @param price 
     * @return a list of cocktails that match the price
     */
   List<Cocktail> searchByPrice(Integer price);


    

}
