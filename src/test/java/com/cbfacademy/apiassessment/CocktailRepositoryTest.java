package com.cbfacademy.apiassessment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cbfacademy.apiassessment.cocktailapi.Cocktail;
import com.cbfacademy.apiassessment.cocktailapi.JsonCocktailRepository;
import com.fasterxml.jackson.databind.ObjectMapper;



public class CocktailRepositoryTest {


    @Mock
    private ObjectMapper objectMapper; // Mock ObjectMapper

    @Mock
    private Random random; // Mock Random object


   public CocktailRepositoryTest() {
       MockitoAnnotations.openMocks(this); // Initialize mocks
    }


    @InjectMocks
    private JsonCocktailRepository cocktailRepository; // Inject the repository to be tested

    // Mock the behavior of the repository's searchByPrice method to return a list of cocktails within the price range
    private List<Cocktail> defaultCocktails = Arrays.asList(
        new Cocktail(
            "Gin and Tonic",
            List.of("2 oz Gin", "4 oz Tonic Water", "Fresh Lime Wedge (for garnish)"),
            true,
            "Low",
            "Build over ice in a highball glass, stir gently, and garnish with a lime wedge.",
            14.00
        ),
        new Cocktail(
            "Cosmopolitan",
            List.of("1.5 oz Vodka", "1 oz Triple Sec", "0.5 oz Fresh Lime Juice", "1 oz Cranberry Juice"),
            false,
            "Medium",
            "Shake all ingredients with ice and strain into a chilled martini glass. Garnish with a twist of lime.",
            15.00
        ),
        new Cocktail(
        "Expresso Martini", 
        List.of("1.5 oz Vodka", "1 oz Coffee Liqueur", "1 oz Freshly Brewed Espresso", "0.5 oz Simple Syrup"), 
        false, "High", 
        "Shake all ingredients with ice and strain into a chilled martini glass", 
        15.00)
    );

    @Test
    void testSearchByPrice(){

        // Mock the behavior of the repository's searchByPrice method
        when(cocktailRepository.searchByPrice(14.00)).thenReturn(defaultCocktails);

        // Call the searchByPrice method of the repository with a price parameter
        double price = 14.00; // Price to search for
        List<Cocktail> cocktails = cocktailRepository.searchByPrice(price);
        ResponseEntity<List<Cocktail>> response = new ResponseEntity<>(cocktails, HttpStatus.OK);

        // Assert the response
        assertEquals(HttpStatus.OK, response.getStatusCode()); // Check if the response status is OK
        assertNotNull(response.getBody()); // Ensure the response body is not null
    }


    @Test
    void testSelectRandomCocktail() {
        // Mock the behavior of the Random object
        when(random.nextInt(3)).thenReturn(1); 

        // Call the selectRandomCocktail method of the repository with the list of cocktails
        Cocktail selectedCocktail = cocktailRepository.selectRandomCocktail(defaultCocktails);

        // Assert that the selected cocktail is not null
        assertNotNull(selectedCocktail);

    }

    @Test
    void testSearchByCocktailName() {

        // Mock the behavior of the repository's searchByCocktailName method
        String expectedCocktailName = "Cosmopolitan";

        when(cocktailRepository.searchByCocktailName(expectedCocktailName)).thenReturn(

            Arrays.asList(defaultCocktails.get(1)) // Return the Cosmopolitan cocktail

        );

        // Call the searchByCocktailName method of the repository with the cocktail name parameter
        List<Cocktail> cocktails = cocktailRepository.searchByCocktailName(expectedCocktailName);

        // Assert the response
        assertNotNull(cocktails); // Ensure the response is not null
        assertEquals(1, cocktails.size()); // Ensure only one cocktail is returned
        assertEquals(expectedCocktailName, cocktails.get(0).getName()); // Ensure the returned cocktail is Cosmopolitan
    }
    
}
