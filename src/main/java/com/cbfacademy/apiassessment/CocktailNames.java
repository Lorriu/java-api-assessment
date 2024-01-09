package com.cbfacademy.apiassessment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class CocktailNames {


    //path to JSON file
    private final String jsonFilePath = "cocktailsList.json";
    //ObjectMapper to read and write JSON file
    private final ObjectMapper objectMapper = new ObjectMapper();
    //JsonNode object used to manipulate JSON object
    private JsonNode root;

    public CocktailNames(){


    }

    


    //get all cocktail names in the JSON file  
    public List<String> getAllCocktailsName() {

        //create a new Array list called cocktailsNameList
        List<String> cocktailsNamesList = new ArrayList<>();
        //Get the root node "cocktailsList"
        JsonNode cocktailsListRoot = root.get("cocktailsList"); 


        try {
            if (cocktailsListRoot != null && cocktailsListRoot.isArray()) {

                // Iterate through each node in the 'cocktailsList' array and get the names
                for (JsonNode cocktail : cocktailsListRoot) {
                
                    String name = cocktail.get("name: ").asText();

                    // Add the name to the list
                    cocktailsNamesList.add(name); 
                }
            } else { 
                throw new IOException("'cocktailsList' not found or is not an array");
            }
            } catch (IOException e) {
        
                System.err.println("Error accessing 'cocktailsList': " + e.getMessage());
            
            }

            return cocktailsNamesList;
    }


    
}
