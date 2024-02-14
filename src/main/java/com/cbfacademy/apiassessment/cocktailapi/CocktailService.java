package com.cbfacademy.apiassessment.cocktailapi;

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
     * Retrieve an Cocktail by its name
     *
     * @param name The ID of the Cocktail to retrieve.
     * @return The Cocktail with the specified name, or null if not found.
     */
    Cocktail getCocktail(String name);

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
     * @param name         The ID of the Cocktail to update.
     * @param updatedCocktail The updated Cocktail object.
     * @return The updated Cocktail, or null if the ID is not found.
     */
    Cocktail updateCocktail(String name, Cocktail updatedCocktail);

    /**
     * Delete an Cocktail by its name.
     *
     * @param name The name of the Cocktail to delete.
     */
    void deleteCocktail(String name);

}
    

