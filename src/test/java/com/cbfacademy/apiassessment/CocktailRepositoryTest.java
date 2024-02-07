package com.cbfacademy.apiassessment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.cbfacademy.apiassessment.cocktailapi.Cocktail;
import com.cbfacademy.apiassessment.cocktailapi.JsonCocktailRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CocktailRepositoryTest {

    private JsonCocktailRepository cocktailRepository;

    @BeforeEach
    public void setUp() {
        // Initialize your repository here
        cocktailRepository = new JsonCocktailRepository(new ObjectMapper());
        
        //List of new Cocktails for testing
        cocktailRepository.loadDataForTesting(Arrays.asList(

            new Cocktail("Gin and Tonic", 
            Arrays.asList("2 oz Gin", "4 oz Tonic Water", "Fresh Lime Wedge (for garnish)"),
            true, 
            "Low", 
            "Build over ice in a highball glass, stir gently, and garnish with a lime wedge.", 
            14.00),

            new Cocktail( "Cosmopolitan",
            Arrays.asList("1.5 oz Vodka", "1 oz Triple Sec", "0.5 oz Fresh Lime Juice", "1 oz Cranberry Juice"),
            false,
            "Medium",
            "Shake all ingredients with ice and strain into a chilled martini glass. Garnish with a twist of lime.",
            15.00),

            new Cocktail("Expresso Martini", 
            Arrays.asList("1.5 oz Vodka", "1 oz Coffee Liqueur", "1 oz Freshly Brewed Espresso", "0.5 oz Simple Syrup"), 
            false, 
            "High", 
            "Shake all ingredients with ice and strain into a chilled martini glass", 
            15.00)
        ));
    }

    @Test
    void testSearchByPrice() {
        // Directly test the method with the expected behavior
        List<Cocktail> results = cocktailRepository.searchByPrice(14.00);
        assertEquals(1, results.size());
        assertEquals("Gin and Tonic", results.get(0).getName());
    }

    @Test
    void testSelectRandomCocktail() {
        // Since selectRandomCocktail is inherently random, this test might need to be approached differently
        // For example, you could test if the method returns a result without throwing an exception
        Cocktail cocktail = cocktailRepository.selectRandomCocktail(cocktailRepository.retrieveAll());
        assertNotNull(cocktail);
    }

    @Test
    void testSearchByCocktailName() {
        List<Cocktail> results = cocktailRepository.searchByCocktailName("Gin and Tonic");
        assertEquals(1, results.size());
        assertEquals("Gin and Tonic", results.get(0).getName());
    }
}