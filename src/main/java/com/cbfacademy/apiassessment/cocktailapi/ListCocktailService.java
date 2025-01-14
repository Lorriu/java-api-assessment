package com.cbfacademy.apiassessment.cocktailapi;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbfacademy.apiassessment.cocktailapi.core.PersistenceException;



@Service
public class ListCocktailService implements CocktailService {

    private final JsonCocktailRepository cocktailRepository;

    @Autowired
    public ListCocktailService(JsonCocktailRepository cocktailRepository) {
        this.cocktailRepository = cocktailRepository;
    }

    @Override
    public List<Cocktail> getAllCocktails() {

        try {
            return cocktailRepository.retrieveAll();
        } catch (PersistenceException e) {
            // Handle exception or return an empty list
            e.getMessage();
            return new ArrayList<>();
        }
    }

    @Override
    public Cocktail getCocktail(UUID id) {

        try {
            return cocktailRepository.retrieve(id);
        } catch (IllegalArgumentException e) {
            // Handle exception or return null if not found
            e.getMessage();
            return null;
        } catch (Exception e) {
            // Handle other exceptions
            e.getMessage();
            return null;
        }
       
    }

    @Override
    public Cocktail getCocktail(String name) {

        try {
            return cocktailRepository.retrieveByName(name);
        } catch (IllegalArgumentException e) {
            // Handle exception or return null if not found
            e.getMessage();
            return null;
        } catch (Exception e) {
            // Handle other exceptions
            e.getMessage();
            return null;
        }
       
    }

    @Override
    public Cocktail createCocktail(Cocktail cocktail) {

        try {
            return cocktailRepository.create(cocktail);
        } catch (IllegalArgumentException | PersistenceException e) {
            // Handle exception or return null
            e.getMessage();
            return null;
        }

    }

    @Override
    public Cocktail updateCocktail(String name, Cocktail updatedCocktail) {

        try {
            return cocktailRepository.update(updatedCocktail);
        } catch (IllegalArgumentException | PersistenceException e) {
            // Handle exception or return null
            e.getMessage();
            return null;
        }
    }

    @Override
    public void deleteCocktail(String name) {
        
        Cocktail cocktailToDelete = getCocktail(name);
        if (cocktailToDelete != null) {
            try {
                cocktailRepository.delete(cocktailToDelete);
            } catch (IllegalArgumentException | PersistenceException e) {
                // Handle exception
                e.getMessage();
            }
        }
    }

}



