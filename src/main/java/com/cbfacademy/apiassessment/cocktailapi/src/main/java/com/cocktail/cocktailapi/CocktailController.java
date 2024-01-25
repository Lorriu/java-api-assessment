package com.cbfacademy.apiassessment.cocktailapi.src.main.java.com.cocktail.cocktailapi;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cbfacademy.apiassessment.cocktailapi.src.main.java.com.cocktail.cocktailapi.core.ApiErrorResponse;
import com.cbfacademy.apiassessment.cocktailapi.src.main.java.com.cocktail.cocktailapi.core.CocktailNotFoundException;

//Controller for handling HTTP request
@RestController
//create global exception
@ControllerAdvice
@ResponseStatus
//base URI path for mappings
@RequestMapping("/api/cocktails")

public class CocktailController {

     //Cocktail service dependency
    private final CocktailService cocktailService;

    @Autowired
    // Constructor for the CocktailController, receiving a CocktailService instance
    public CocktailController(CocktailService cocktailService) {
        this.cocktailService = cocktailService;
    }

    // Handling HTTP GET request to retrieve all Cocktails
    @GetMapping
    public ResponseEntity<List<Cocktail>> getAllCocktails() {

        List<Cocktail> cocktails = CocktailService.getAllCocktails();
        return ResponseEntity.ok().body(cocktails);
    }

    // Handling HTTP GET request to retrieve a specific Cocktail by its ID
    @GetMapping("/{id}")
    // Extracting the ID from the URI path and passing it as a method parameter
    public ResponseEntity<Cocktail> getCocktail(@PathVariable("id") UUID id) {
        
        try {

            Cocktail cocktail = cocktailService.getCocktail(id);
            return ResponseEntity.ok().body(cocktail);

        } catch (CocktailNotFoundException e) {

            //return ResponseEntity.notFound().build();
            e.getMessage();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            
        }
       
    }

    // Handling HTTP POST request to add a new Cocktail
    @PostMapping
    // Deserializing the request body (JSON) into an Cocktail object
    public ResponseEntity<Cocktail> createIOU(@RequestBody Cocktail cocktail) {
       
        try {

            Cocktail created = cocktailService.createCocktail(cocktail);
            URI endpoint = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(endpoint).body(created);

        } catch (CocktailNotFoundException e) {

            e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Handling HTTP PUT request to update an existing Cocktail by its ID
    @PutMapping("/{id}")
    // Extracting the ID from the URI path and updating the corresponding Cocktail
    public ResponseEntity<Cocktail> updateCocktail(@PathVariable UUID id, @RequestBody Cocktail updatedCocktail) {
        
            try {
                
                updatedCocktail.setId(id); // Ensure the ID is set to the correct value
                
                return new ResponseEntity<>(cocktailService.updateCocktail(id, updatedCocktail), HttpStatus.OK);

            } catch (CocktailNotFoundException e) {

                //return ResponseEntity.notFound().build();
                e.getMessage();
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
               
    }

    // Handling HTTP DELETE request to delete a Cocktail by its ID
    @DeleteMapping("/{id}")
    // Extracting the ID from the URI path and deleting the corresponding Cocktail
    public ResponseEntity<?> deleteCocktail(@PathVariable UUID id) {

    try {
        cocktailService.deleteCocktail(id);

        return ResponseEntity.noContent().build();

    } catch (CocktailNotFoundException e) {
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiErrorResponse("Cocktail not found"));
    }
}




    
}
