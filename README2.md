# **Cocktail API**

<div style="text-align:center;">
  <img width="600" height="350" alt="Cocktail sign" src="https://plus.unsplash.com/premium_photo-1673512328046-2f773b25c104?q=80&w=2970&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"> 
</div>

## **Introduction**

I was tasked with creating an API that includes:


1. At least one algorithm
1. Unit test at least one class
1. Store the data in a JSON file 
1. Exception handling 
1. Evidence of inheritance
1. Good use of HTTP Protocols - methods, request and response, have full CRUD operations supported 
1. Documentation

<div style="text-align:center;">
  <img width="700" height="350" alt="Variety of Cocktails" src="https://images.unsplash.com/photo-1605270012917-bf157c5a9541?q=80&w=2969&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D">
</div>

I decided to create a Cocktail API, that can be used to read, add, update and delete a list of cocktails. There are multiple use cases this API can be used for including a cocktail recipe app/website, a cocktail ordering system, that could be used by resturants or bars, it can be an add on feature to a menu or used for inventory or teaching bartenders. 

I will decribe how each item was included in the project below:

## At least one algoritm

I have multiple algorithm's located in my [JsonCocktailReposity](https://github.com/Lorriu/java-api-assessment/blob/main/src/main/java/com/cbfacademy/apiassessment/cocktailapi/JsonCocktailRepository.java). I used linear search to locate the specified name (searchByCocktailName()), alchohol strength (searchByAlcoholStrength()) and price (searchByPrice()), as well as in the update() method and multiple other methods in this class to implement algorithms to locate the desired cocktail.

## Unit test at least one class

I created a unit test to test the repository class [CocktailRepositoryTest](https://github.com/Lorriu/java-api-assessment/blob/main/src/test/java/com/cbfacademy/apiassessment/CocktailRepositoryTest.java) In this class I tested:

1. A test to search the cocktails by Price
1. A test to check the random cocktail method selected a random cocktail.
1. A test to check the when searching cocktail by name the correct cocktail was selected 

## Store the data in a JSON file 

The data is stored in a JSON file named [cocktailslist](https://github.com/Lorriu/java-api-assessment/blob/main/src/main/java/com/cbfacademy/apiassessment/cocktailapi/cocktailsList.json)

<div style="text-align:center;">
  <img width="700" height="700" alt="Cocktails Menu" src="https://nordicpostercollective.se/cdn/shop/products/classic-cocktails-1_1024x1024.jpg?v=1577634600">
</div>


## Exception handling

The [Core](https://github.com/Lorriu/java-api-assessment/tree/main/src/main/java/com/cbfacademy/apiassessment/cocktailapi/core) repository has the exception handling classes that I created, these exceptions where mainly used in my [JsonCocktailRepository](https://github.com/Lorriu/java-api-assessment/blob/main/src/main/java/com/cbfacademy/apiassessment/cocktailapi/JsonCocktailRepository.java) to make sure that the ID or item requested is not null or is a valid entry. 

## Evidence of inheritance

An example of Inheritance being was used within this project is the [CocktailRepository](https://github.com/Lorriu/java-api-assessment/blob/main/src/main/java/com/cbfacademy/apiassessment/cocktailapi/CocktailRepository.java) class extending the repository class. 

## Good use of HTTP Protocols 

In the [CocktailController](https://github.com/Lorriu/java-api-assessment/blob/main/src/main/java/com/cbfacademy/apiassessment/cocktailapi/CocktailController.java) there methods that define endpoints for the following operations are:

| Method   | URL              | Description          |
| -------- | ---------------- | -------------------- |
| `GET`    | `/api/cocktails`      | Get all Cocktails         |
| `GET`    | `/api/cocktails/{id}` | Get a Cocktail by id     |
| `POST`   | `/api/cocktails`      | Add a Cocktail          |
| `PUT`    | `/api/cocktails/{id}` | Replace a Cocktail by Id |
| `DELETE` | `/api/cocktails/{id}` | Delete a Cocktail by id  |


The [ListCocktailService](https://github.com/Lorriu/java-api-assessment/blob/main/src/main/java/com/cbfacademy/apiassessment/cocktailapi/ListCocktailService.java) has the full methods to perform CRUD operations. 


A representative HTTP response will look something like this:
```JSON
{
    "id": "8fg692-2322-a59e98efg64f",
    "name": "Margarita",
        "ingredients": [
          "2 oz Tequila",
          "1 oz Fresh Lime Juice",
          "0.75 oz Triple Sec"
        ],
        "fresh_fruit": true,
        "alcohol_strength": "Medium",
        "details": "Serve on the rocks with a salted rim.",
        "price": 14.00
}
```

<div style="text-align:center;">
  <img width="600" height="300" alt="Cocktail sign" src="https://plus.unsplash.com/premium_photo-1674837818413-bc14a2c5d6e1?q=80&w=2970&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"> 
</div>

## If you would like to collaborate on this project here are the steps to get started:

### Setup

#### 1. Clone the Repository

```sh
git clone [REPO_URL]
cd [REPO_NAME]
```

Replace [REPO_URL] with the link to your GitHub repository and [REPO_NAME] with the repository's name.

#### 2. Install Dependencies

Open a terminal at the root of the repo directory and run the following command to install the dependencies:

```sh
./mvnw clean dependency:resolve
```

If you are on a Windows machine, that will be:
```cmd
mvnw clean dependency:resolve
```

You should see console output similar to the following:

```sh
[INFO] Scanning for projects...
[INFO] 
[INFO] -------------------< com.cbfacademy:api-assessment >--------------------
[INFO] Building api-assessment 0.0.1-SNAPSHOT
[INFO]   from pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- clean:3.2.0:clean (default-clean) @ api-assessment ---
[INFO] Deleting /Users/user/Dev/cbfacademy/java-api-assessment/target
...
[truncated output]
...
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  3.060 s
[INFO] Finished at: 2023-10-03T16:18:25+01:00
[INFO] ------------------------------------------------------------------------
```

#### 3. Running the Application

To start the API in VS Code, press `F5` or tap the 'Play' icon for the `api-assessment` app in the Spring Boot Dashboard.

Alternatively, to start the API from the terminal, run the following command:

```sh
./mvnw spring-boot:run
```

Or on Windows:

```cmd
mvnw spring-boot:run
```

You should see console output similar to the following (press `Ctrl + C` to exit):

```sh
[INFO] Scanning for projects...
[INFO] 
[INFO] -------------------< com.cbfacademy:api-assessment >--------------------
[INFO] Building api-assessment 0.0.1-SNAPSHOT
[INFO]   from pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- clean:3.2.0:clean (default-clean) @ api-assessment ---
[INFO] Deleting /Users/gary/Dev/cbfacademy/java-api-assessment/target
[INFO] 
[INFO] >>> spring-boot:3.1.4:run (default-cli) > test-compile @ api-assessment >>>
[INFO] 
[INFO] --- resources:3.3.1:resources (default-resources) @ api-assessment ---
[INFO] Copying 1 resource from src/main/resources to target/classes
[INFO] Copying 0 resource from src/main/resources to target/classes
...
[truncated output]
...
2023-10-03T17:17:34.413+01:00  INFO 35536 --- [  restartedMain] .e.DevToolsPropertyDefaultsPostProcessor : For additional web related logging consider setting the 'logging.level.web' property to 'DEBUG'
2023-10-03T17:17:34.751+01:00  INFO 35536 --- [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 8080 (http)
2023-10-03T17:17:34.756+01:00  INFO 35536 --- [  restartedMain] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2023-10-03T17:17:34.756+01:00  INFO 35536 --- [  restartedMain] o.apache.catalina.core.StandardEngine    : Starting Servlet engine: [Apache Tomcat/10.1.13]
2023-10-03T17:17:34.777+01:00  INFO 35536 --- [  restartedMain] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2023-10-03T17:17:34.778+01:00  INFO 35536 --- [  restartedMain] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 364 ms
2023-10-03T17:17:34.898+01:00  INFO 35536 --- [  restartedMain] o.s.b.d.a.OptionalLiveReloadServer       : LiveReload server is running on port 35729
2023-10-03T17:17:34.907+01:00  INFO 35536 --- [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2023-10-03T17:17:34.911+01:00  INFO 35536 --- [  restartedMain] com.cbfacademy.apiassessment.App         : Started App in 0.643 seconds (process running for 0.786)
```

Open your browser and navigate to `http://localhost:8080`.