package com.cbfacademy.apiassessment.cocktailapi.src.main.java.com.cocktail.cocktailapi.core;



public class CocktailNotFoundException  extends RuntimeException{


        public CocktailNotFoundException() {
            super("Cocktail not found");
        }
    
        public CocktailNotFoundException(String message) {
            super(message);
        }

        public String getBody() {
            return getMessage();
        }

    
}
