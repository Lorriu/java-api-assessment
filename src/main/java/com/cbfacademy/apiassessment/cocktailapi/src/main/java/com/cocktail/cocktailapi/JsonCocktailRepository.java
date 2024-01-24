package com.cbfacademy.apiassessment.cocktailapi.src.main.java.com.cocktail.cocktailapi;

import com.cbfacademy.apiassessment.cocktailapi.src.main.java.com.cocktail.cocktailapi.core.PersistenceException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public class JsonCocktailRepository implements CocktailRepository {
    private final String filePath;
    private final ObjectMapper objectMapper;
    private final List<Cocktail> cocktails;

    public JsonCocktailRepository(@Value("${cocktailsList.json}") String filePath) {
        this.filePath = filePath;
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        cocktails = loadDataFromJson();
    }

    private List<Cocktail> loadDataFromJson() {
        try {
            File file = new File(filePath);

            if (file.exists()) {
                return objectMapper.readValue(file, new TypeReference<List<Cocktail>>() {
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    private void saveDataToJson() {
        try {
            objectMapper.writeValue(new File(filePath), cocktails);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Iterable<Cocktail> findAll() {
        return cocktails;
    }

    @Override
    public List<Cocktail> retrieveAll() throws PersistenceException {
        return cocktails;
    }

    @Override
    public Cocktail retrieve(UUID id) throws IllegalArgumentException, PersistenceException {
        return cocktails.stream()
                .filter(cocktail -> cocktail.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Cocktail create(Cocktail entity) throws IllegalArgumentException, PersistenceException {
        entity.setId(UUID.randomUUID());
        cocktails.add(entity);
        saveDataToJson();
        return entity;
    }

    @Override
    public void delete(Cocktail entity) throws IllegalArgumentException, PersistenceException {
        cocktails.removeIf(cocktail -> cocktail.getId().equals(entity.getId()));
        saveDataToJson();
    }

    @Override
    public Cocktail update(Cocktail entity) throws IllegalArgumentException, PersistenceException {
        UUID id = entity.getId();
        if (id == null) {
            throw new IllegalArgumentException("Cocktail ID cannot be null for update operation.");
        }

        int index = IntStream.range(0, cocktails.size())
                .filter(i -> cocktails.get(i).getId().equals(id))
                .findFirst()
                .orElse(-1);

        if (index != -1) {
            cocktails.set(index, entity);
            saveDataToJson();
            return entity;
        } else {
            throw new IllegalArgumentException("Cocktail not found for update.");
        }
    }

    @Override
    public List<Cocktail> searchByCocktailName(String name) {
        return cocktails.stream()
                .filter(cocktail -> cocktail.getName().equals(name))
                .collect(Collectors.toList());
    }

    @Override
    public List<Cocktail> searchByAlcoholStrength(String strength) {
        return cocktails.stream()
                .filter(cocktail -> cocktail.getAlcoholStrength().equals(strength))
                .collect(Collectors.toList());
    }
}