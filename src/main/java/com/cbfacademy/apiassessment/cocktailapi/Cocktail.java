package com.cbfacademy.apiassessment.cocktailapi;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class Cocktail {

    private UUID id;
    private String name;
    private List<String> ingredients;
    private boolean freshFruit;
    private String alcoholStrength;
    private String details;
    private int price;


     // Constructor to initialize the list
        public Cocktail() {
            this.id = UUID.randomUUID();
            this.ingredients = new ArrayList<>();
         }

    //Constructot with name
        public Cocktail(String name) {

            this(); // Invoke the default constructor
            this.name = name;
         }
  

    //constructor with parameters
        public Cocktail(String name, List<String> ingredients, boolean freshFruit, String alcoholStrength, String details, int price) {

            //generate random UUID
            this.id = UUID.randomUUID();
            this.name = name;
            this.ingredients = ingredients;
            this.freshFruit = freshFruit;
            this.alcoholStrength = alcoholStrength;
            this.details = details;
            this.price = price;
        }

    // Getter methods
        public UUID getId(){

            return id;
        }
        
        public String getName() {
            return name;
        }

        public List<String> getIngredients() {
            return ingredients;
        }

        public boolean hasFreshFruit() {
            return freshFruit;
        }

        public String getAlcoholStrength() {
            return alcoholStrength;
        }

        public String getDetails() {
            return details;
        }

        public Integer getPrice(){
            return price;
        }

    // Setter methods

        public void setId(UUID id) {
            this.id = id;
        }

         public void setName(String name) {
            this.name = name;
        }

        public void setIngredients(List<String> ingredients) {
            this.ingredients = ingredients;
        }

        public void setFreshFruit(boolean hasFreshFruit) {
            this.freshFruit = hasFreshFruit;
        }

        public void setAlcoholStrength(String strength) {
            this.alcoholStrength = strength;
        }

        public void setDetails(String details) {
            this.details = details;
        }

        public void setPrice(Integer price) {
            this.price = price;
        }

        //toString method to display details on cocktail
        @Override
        public String toString() {
            return "Cocktail{ name: "+ name + 
                    ", \ningredients: "+ ingredients + 
                    ", \nfreshFruit: "+ freshFruit + 
                    ", \nalcoholStrength: "+ alcoholStrength + 
                    ", \ndetails: "+ details +
                    ", \nprice: "+ price + '}';
        }

}
