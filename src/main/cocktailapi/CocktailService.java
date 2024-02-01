package cocktailapi.src.main.java.com.cocktail.cocktailapi

import java.util.List;
import java.util.UUID;


public interface CocktailService {

    /**
     * Retrieve a list of all Cocktails.
     *
     * @return A list of all Cocktails.
     */
    List<Cocktail> getAllCocktails();

    /**
     * Retrieve an Cocktail by its ID.
     *
     * @param id The ID of the Cocktail to retrieve.
     * @return The Cocktail with the specified ID, or null if not found.
     */
    Cocktail getCocktail(UUID id);

    /**
     * Create a new Cocktail.
     *
     * @param cocktail The Cocktail object to create.
     * @return The created Cocktail.
     */
    Cocktail createCocktail(Cocktail cocktail);

    /**
     * Update an existing Cocktail by its ID.
     *
     * @param id         The ID of the Cocktail to update.
     * @param updatedCocktail The updated Cocktail object.
     * @return The updated Cocktail, or null if the ID is not found.
     */
    Cocktail updateCocktail(UUID id, Cocktail updatedCocktail);

    /**
     * Delete an Cocktail by its ID.
     *
     * @param id The ID of the Cocktail to delete.
     */
    void deleteCocktail(UUID id);

}
    

