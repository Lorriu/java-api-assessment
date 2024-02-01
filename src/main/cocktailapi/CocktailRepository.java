package cocktailapi.src.main.java.com.cocktail.cocktailapi;

import java.util.List;
import java.util.UUID;

import cocktailapi.src.main.java.com.cocktail.cocktailapi.core.Repository;

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
     * @param cocktails t
     * @return a random cocktail
     */
    Cocktail selectRandomCocktail(List<Cocktail> cocktails);


    

}