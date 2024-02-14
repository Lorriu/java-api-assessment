package com.cbfacademy.apiassessment.cocktailapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class CocktailapiApplication {

	public static void main(String[] args) {
		
		ConfigurableApplicationContext context = SpringApplication.run(CocktailapiApplication.class, args);

		// Get the JsonCocktailRepository bean from the context
        JsonCocktailRepository jsonCocktailRepository = context.getBean(JsonCocktailRepository.class);

        // Set the file path
        jsonCocktailRepository.setFilePath("./src/main/java/com/cbfacademy/apiassessment/cocktailapi/cocktailsList.json");


	}

}
