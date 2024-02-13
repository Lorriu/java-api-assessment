package com.cbfacademy.apiassessment.cocktailapi;

import com.cbfacademy.apiassessment.cocktailapi.core.PersistenceException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.FileNotFoundException;
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
    private String filePath;
    //ObjectMapper for JSON serialization/deserialization
    private ObjectMapper objectMapper;
    //List to hold cocktails in memory
    private List<Cocktail> cocktails;



    //Constructor to initialize the repository with file path and load data from JSON
    public JsonCocktailRepository(ObjectMapper objectMapper) {

        this.objectMapper = objectMapper;
        this.objectMapper.registerModule(new JavaTimeModule());
        this.cocktails = new ArrayList<>();
        //cocktails = loadDataFromJson();

    }

   
    //set file path
    public void setFilePath(String filePath){

        validateFilePath(filePath); // Validate the file path
        this.filePath = filePath;
        this.cocktails = loadDataFromJson();
    }

    //get file path
    public String getFilePath() {
        return filePath;
    }
    
    // Validate the file path
    private void validateFilePath(String filePath) {

        File file = new File(filePath);
        

        if (!file.exists() || !file.isFile()) {
            
            throw new IllegalArgumentException("Invalid file path: " + filePath);
        }
    }

    //Load cocktail data from JSON file into the in-memory list
    public List<Cocktail> loadDataFromJson() {
        try {
            File file = new File(filePath);

            //Read JSON file and convert to List<Cocktail>
            if (!file.exists()) {
                    throw new FileNotFoundException("File not found: " + filePath);
            }
                //return List<Cocktail>.class;
            //eturn objectMapper.readValue(file, new ArrayList<Cocktail>());

            //If the file exisits, read the values from file and change to List of Cocktails and assign
            return objectMapper.readValue(file, new TypeReference <List<Cocktail>>() {});

        } catch (IOException e) {
            e.printStackTrace();
        }

        //DEBUGGING TEST Return list of data objectMapper loading fails (concluded the objectMapper was failing)
        //return new ArrayList<>();
        //ArrayList testList = new ArrayList<>();
        //testList.add("sussie");

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
    //Retrieve a cocktail by its unique identifier
    public Cocktail retrieveByName(String name) throws IllegalArgumentException, PersistenceException {
        // Check if the provided ID is null
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null for cocktail retrieval.");
        }

        // Use stream and filter to find the cocktail with the specified ID
        Optional<Cocktail> foundCocktail = cocktails.stream()
            .filter(cocktail -> cocktail.getName().equals(name))
            .findFirst();

        // If a cocktail with the specified ID is found, return it; otherwise, throw PersistenceException
        if (foundCocktail.isPresent()) {
            return foundCocktail.get();
        } else {
            throw new PersistenceException("Cocktail with name " + name + " not found for retrieval.");
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
    //LinearSearch for cocktails by name in the in-memory list
    public List<Cocktail> searchByCocktailName(String name) {
        return cocktails.stream()
                .filter(cocktail -> cocktail.getName().equals(name))
                .collect(Collectors.toList());
    }

    @Override
    //LinearSearch for cocktails by alcohol strength in the in-memory list
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

    @Override
    //Linear search for a cocktail by price point
    public List<Cocktail> searchByPrice(double price) {

        //filter cocktails based on the specified price
        return cocktails.stream()
            .filter(cocktail -> cocktail.getPrice() == price)
            //Collect the matching cocktails into a new list
            .collect(Collectors.toList());

    }

    // Method to manually load data for testing
    public void loadDataForTesting(List<Cocktail> testCocktails) {

        // Clear existing data
        this.cocktails.clear();
        // Add test data
        this.cocktails.addAll(testCocktails);

    }



}