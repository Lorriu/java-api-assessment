package com.cbfacademy.apiassessment;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cbfacademy.apiassessment.cocktailapi.Cocktail;
import com.cbfacademy.apiassessment.cocktailapi.CocktailController;
import com.cbfacademy.apiassessment.cocktailapi.JsonCocktailRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.UUID;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CocktailControllerTests {


    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private JsonCocktailRepository cocktailRepository;

    @Autowired
    private CocktailController cocktailController;

   

    @Test
    void testGetCocktailById() {
        //Test case to check the response when calling the endpoint to get a specific cocktail by ID
        
        // Prepare test data
        UUID id = UUID.randomUUID();
    
        // Make a GET request to retrieve the cocktail by ID
        String url = "http://localhost:" + port + "/api/cocktails/" + id;
        ResponseEntity<Cocktail> response = restTemplate.getForEntity(url, Cocktail.class);
    
        //Assert the response
        assertEquals(HttpStatus.OK, response.getStatusCode()); // Check if the response status is OK
        assertNotNull(response.getBody()); // Ensure the response body is not null
    }

    @Test
    void testCreateCocktail() {
        //Test case to check the response when calling the endpoint to create a new cocktail

        // Create a new Cocktail object
        Cocktail newCocktail = new Cocktail(
            "Expresso Martini", 
            List.of("1.5 oz Vodka", "1 oz Coffee Liqueur", "1 oz Freshly Brewed Espresso", "0.5 oz Simple Syrup"), 
            false, "High", 
            "Shake all ingredients with ice and strain into a chilled martini glass", 
            15.00);

        // Mock the behavior of the repository's create method to return the new cocktail
        when(cocktailRepository.create(newCocktail)).thenReturn(newCocktail);

        // Call the createCocktail method of the controller
        ResponseEntity<Cocktail> response = cocktailController.createCocktail(newCocktail);

        // Assert the response
        assertEquals(HttpStatus.CREATED, response.getStatusCode()); // Check if the response status is CREATED
        assertNotNull(response.getBody()); // Ensure the response body is not null
    }

    @Test
    void testUpdateCocktail() {
        //Test case to check the response when calling the endpoint to update a existing cocktail

         // New cocktail data
         Cocktail updatedCocktail = new Cocktail(
            "Cosmopolitan",
            List.of("1.5 oz Vodka", "1 oz Triple Sec", "0.5 oz Fresh Lime Juice", "1 oz Cranberry Juice"),
            false,
            "Medium",
            "Shake all ingredients with ice and strain into a chilled martini glass. Garnish with a twist of lime.",
            15.00
        );

        // ID of the cocktail to update (the first one in the list)
        UUID cocktailId = cocktailRepository.retrieveAll().get(0).getId();

        // Call the updateCocktail method of the controller
        ResponseEntity<?> response = cocktailController.updateCocktail(cocktailId, updatedCocktail);

        // Assert the response
        assertEquals(HttpStatus.OK, response.getStatusCode()); // Check if the response status is OK
        assertEquals(updatedCocktail, response.getBody()); // Verify that the updated cocktail is returned
    }

}

    
