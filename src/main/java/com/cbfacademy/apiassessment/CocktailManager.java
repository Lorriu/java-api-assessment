package com.cbfacademy.apiassessment;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

//create a class that performs CRUD operations 

public class CocktailManager {

    //path to JSON file
    private final String jsonFilePath = "cocktailsList.json";
    //ObjectMapper to read and write JSON file
    private final ObjectMapper objectMapper = new ObjectMapper();
    //JsonNode object used to manipulate JSON object
    private JsonNode root;

    //cocktail manager constructor
    public CocktailManager() throws IOException {
        loadJsonData();
    }

    //Load the file and display the data
    private void loadJsonData() {

        //create a new file object with the JSON file inside
        File file = new File(jsonFilePath);

            try {
                //risky code checking if the file exists
                if (file.exists()) {
                    //if the file exists, read the file and place it in the root JSON object
                    root = objectMapper.readTree(file);
                } else {
                    //if it doesnt exsist create an empty root file that can hold an array, (so cocktails can be added)
                    root = objectMapper.createObjectNode().putArray("cocktailsList");
                }
            } catch (IOException e) {
            // Handle the IOException 
                System.err.println("Error loading file: " + e.getMessage());
            }
    }

    //Write on the JSON root file
    private void saveJsonRoot() throws IOException {

        //write content from the root to the JSON file
        objectMapper.writeValue(new File(jsonFilePath), root);
    }

    // Create cocktail
    public void createCocktail(Cocktail newCocktail) {
        // Add new Cocktail to the JSON document
        try {

            //gets the cocktails ArrayNode from the root
            ArrayNode cocktailsList = (ArrayNode) root.get("cocktailsList");
            //creates a new objectNode where the new created cocktail will go
            ObjectNode cocktailNode = objectMapper.createObjectNode();

            //add properties
            cocktailNode.put("name: ", newCocktail.getName());
            //add property thats an Array List using .putPOJO
            cocktailNode.putPOJO("ingredients: ", newCocktail.getIngredients());
            cocktailNode.put("fresh_fruit: ", newCocktail.hasFreshFruit());
            cocktailNode.put("alcohol_strength: ", newCocktail.getAlcoholStrength());
            cocktailNode.put("details: ", newCocktail.getDetails());

            //adds the new cocktail objectNode to the existing list of cocktails in the JSON file
            cocktailsList.add(cocktailNode);
            saveJsonRoot();

        } catch (IOException e) {
        // Handle the IOException
            System.err.println("Error creating cocktail: " + e.getMessage());
        }       
    }

    // Read 
    public Cocktail getCocktailByName(String name) {
        // Retrieve cocktail details based on the name


        // Return the Cocktail object
        return
    }

    // Update
    public void updateCocktail(String name, Cocktail updatedCocktail) {
        // Update the details of the cocktail
        

    }

    // Delete 
    public void deleteCocktail(String name) {
        // Delete the cocktail from the JSON document
        

    }

    
}
