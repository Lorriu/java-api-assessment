package com.cbfacademy.apiassessment.cocktailapi.src.main.java.com.cocktail.cocktailapi;

import com.cbfacademy.apiassessment.cocktailapi.src.main.java.com.cocktail.cocktailapi.core.PersistenceException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public class JsonCocktailRepository implements CocktailRepository {

    //File path for storing cocktail data in JSON
    private final String filePath;
    //ObjectMapper for JSON serialization/deserialization
    private final ObjectMapper objectMapper;
    //List to hold cocktails in memory
    private final List<Cocktail> cocktails;


    //Constructor to initialize the repository with file path and load data from JSON
    public JsonCocktailRepository(@Value("${cocktailsList.json}") String filePath) {

        validateFilePath(filePath); // Validate the file path
        this.filePath = filePath;
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        cocktails = loadDataFromJson();
    }

    //Validate the file path
    private void validateFilePath(String filePath) {

        File file = new File(filePath);

        if (!file.exists() || !file.isFile()) {
            
            throw new IllegalArgumentException("Invalid file path: " + filePath);
        }
    }

    //Load cocktail data from JSON file into the in-memory list
    private List<Cocktail> loadDataFromJson() {
        try {
            File file = new File(filePath);

            //Read JSON file and convert to List<Cocktail>
            if (file.exists()) {
                return objectMapper.readValue(file, new TypeReference<List<Cocktail>>() {
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Return an empty list if data loading fails
        return new ArrayList<>();
    }


    //Save the in-memory list of cocktails back to the JSON file
    private void saveDataToJson() {
        try {
            //Write the list of cocktails to the JSON file
            objectMapper.writeValue(new File(filePath), cocktails);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    @Override
    //Retrieve all cocktails from the in-memory list
    public List<Cocktail> retrieveAll() throws PersistenceException {
        
        // Check if the list of cocktails is null or empty
        if (cocktails == null || cocktails.isEmpty()) {
        // Throw PersistenceException if the list is empty
        throw new PersistenceException("No cocktails found.");
        }

        // Return the list of cocktails
        return cocktails;
    
    }

    @Override
    //Retrieve a cocktail by its unique identifier
    public Cocktail retrieve(UUID id) throws IllegalArgumentException, PersistenceException {
        // Check if the provided ID is null
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null for cocktail retrieval.");
        }

        // Use stream and filter to find the cocktail with the specified ID
        Optional<Cocktail> foundCocktail = cocktails.stream()
            .filter(cocktail -> cocktail.getId().equals(id))
            .findFirst();

        // If a cocktail with the specified ID is found, return it; otherwise, throw PersistenceException
        if (foundCocktail.isPresent()) {
            return foundCocktail.get();
        } else {
            throw new PersistenceException("Cocktail with ID " + id + " not found for retrieval.");
        }

    }

    @Override
    // Create a new cocktail and add it to the in-memory list
    public Cocktail create(Cocktail entity) {

        //Generate a new UUID for the cocktail
        entity.setId(UUID.randomUUID());
        //Add the new cocktail to the in-memory list
        cocktails.add(entity);
        // Save the updated data back to the JSON file
        saveDataToJson();
        // Return the newly created cocktail
        return entity;
    }

    @Override
    // Delete a cocktail from the in-memory list
    public void delete(Cocktail entity) throws IllegalArgumentException, PersistenceException {

        // Check if the provided entity is null
        if (entity == null) {
        throw new IllegalArgumentException("Cannot delete a cocktail that does not exsist.");
        }
        //Remove the specified cocktail from the in-memory list
        boolean removed = cocktails.removeIf(cocktail -> cocktail.getId().equals(entity.getId()));

        //Check if the cocktail was found and removed
        if (!removed) {
        throw new PersistenceException("Cocktail with ID " + entity.getId() + " not found for deletion.");
        }
        //Save the updated data back to the JSON file
        saveDataToJson();
    }

    @Override
    //Update an existing cocktail in the in-memory list
    public Cocktail update(Cocktail entity) throws IllegalArgumentException, PersistenceException {

        // Get the ID of the cocktail to be updated
        UUID id = entity.getId();
        //Check if the ID is null
        if (id == null) {
            throw new IllegalArgumentException("Cocktail ID cannot be null for update operation.");
        }

        //Find the index of the cocktail with the specified ID in the list
        int index = IntStream.range(0, cocktails.size())
                .filter(i -> cocktails.get(i).getId().equals(id))
                .findFirst()
                .orElse(-1);

        //Check if the cocktail was found in the list
        if (index != -1) {
            //Update the cocktail in the list with the new data
            cocktails.set(index, entity);
            //Save the updated data back to the JSON file
            saveDataToJson();
            //Return the updated cocktail
            return entity;

        }  else {
            // Throw an exception if the cocktail is not found for update
            throw new PersistenceException("Cocktail with ID " + id + " not found for update.");
        }
    }

    @Override
    //Search for cocktails by name in the in-memory list
    public List<Cocktail> searchByCocktailName(String name) {
        return cocktails.stream()
                .filter(cocktail -> cocktail.getName().equals(name))
                .collect(Collectors.toList());
    }

    @Override
    //Search for cocktails by alcohol strength in the in-memory list
    public List<Cocktail> searchByAlcoholStrength(String strength) {
        return cocktails.stream()
                .filter(cocktail -> cocktail.getAlcoholStrength().equals(strength))
                .collect(Collectors.toList());
    }

    @Override
    //Select a random cocktail from the provided list
    public Cocktail selectRandomCocktail(List<Cocktail> cocktails) {

            //Create a new Random object to generate random numbers
            Random rand = new Random();

            //Generate a random index within the range of the list size
            int randomIndex = rand.nextInt(cocktails.size());

            //Retrieve the cocktail at the randomly generated index from the list
            return cocktails.get(randomIndex);
        }

}