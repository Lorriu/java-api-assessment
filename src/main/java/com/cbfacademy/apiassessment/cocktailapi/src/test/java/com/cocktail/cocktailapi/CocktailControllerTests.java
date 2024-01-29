package com.cbfacademy.apiassessment.cocktailapi.src.test.java.com.cocktail.cocktailapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CocktailControllerTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testGetAllCocktails() {

        // Write a test case to check the response when calling the endpoint to get all cocktails
        // Use the restTemplate to make a GET request to "/api/cocktails" and assert the expected behavior

        String url = "http://localhost:" + port + "/api/cocktails";
        // Make a GET request using TestRestTemplate
        String response = restTemplate.getForObject(url, String.class);
    
        // Assert the response or perform other assertions based on your test case
        assertNotNull(response);
        assertEquals("Expected Response", response);

    }

    @Test
    void testGetCocktailById() {
        // Write a test case to check the response when calling the endpoint to get a specific cocktail by ID
        // Use the restTemplate to make a GET request to "/api/cocktails/{id}" and assert the expected behavior
    }

    @Test
    void testCreateCocktail() {
        // Write a test case to check the response when calling the endpoint to create a new cocktail
        // Use the restTemplate to make a POST request to "/api/cocktails" with a request body and assert the expected behavior
    }

    @Test
    void testUpdateCocktail() {
        // Write a test case to check the response when calling the endpoint to update an existing cocktail
        // Use the restTemplate to make a PUT request to "/api/cocktails/{id}" with a request body and assert the expected behavior
    }

    @Test
    void testDeleteCocktail() {
        // Write a test case to check the response when calling the endpoint to delete a specific cocktail by ID
        // Use the restTemplate to make a DELETE request to "/api/cocktails/{id}" and assert the expected behavior
    }
}
