package views;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import db.dao.ingredient.IIngredient;
import db.dao.ingredient.IngredienDao;
import db.dao.meal.IMeal;
import db.dao.meal.MealDao;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;


@Named
@ViewScoped
public class Meal implements Serializable {
	
	private db.models.Meal meal ;
	List<db.models.Meal> recommendations = new ArrayList<>() ;
	
	
	public List<db.models.Meal> getRecommendations()
	{
		return recommendations;
	}

	public void setRecommendations(List<db.models.Meal> recommendations) {
		this.recommendations = recommendations;
	}




	@Inject
    private User user;
	
	@Inject
	private Ingredient ingredient;
	
	
	
	@PostConstruct
    public void init() {
        // Initialize the user property
        meal = new db.models.Meal();
        meal.setIngrediens(new ArrayList<>());
        meal.setUser_id(user.getUser().getId());
        fetchRecommendations();
 
        
        //getRecommendations();
    }
	
	public db.models.Meal getMeal() {
		return meal;
	}

	public void setMeal(db.models.Meal meal) {
		this.meal = meal;
	}
	
	public void addIngredient()
	{
		db.models.Ingredient _ingredient = new db.models.Ingredient(ingredient.getIngredient());
		this.meal.getIngrediens().add(_ingredient);		
	}
	
	public void removeIngredient()
	{
		this.meal.getIngrediens().remove(this.meal.getIngrediens().size() - 1);
	}
	
	public void submitMeal()
	{
		db.models.Ingredient ingredient = this.ingredient.getIngredient();
		this.meal.getIngrediens().add(ingredient);
		IMeal meal = new MealDao();
		meal.addMeal(this.meal);
	}
	
	
	
	
	public String fetchRecommendations() {
        
		
		String response = null;
		
		String apiEndpoint = "https://api.spoonacular.com/recipes/findByNutrients?apiKey=697c8d2d9a2443e8a2b006ee564e31ce&query&";
	    String ingredientsParam = "minCalories=50&maxCalories="+(user.getUser().getCalories()/3)+"&minCarbs=10&maxCarbs="+(user.getUser().getCarbs())+"&minProtein=10&maxProtein="+(user.getUser().getProtein())+"&minFat=10&maxFat="+(user.getUser().getFats())+"&number=10";
	    
	    
	    String endpoint = apiEndpoint+ingredientsParam;

	    
	    
	    // send get request
	    try {
	        URL url = new URL(endpoint);
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	        connection.setRequestMethod("GET");

	        // Get API response
	        try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
	        	response = br.lines().collect(Collectors.joining(System.lineSeparator()));
	        }
	        // Parse JSON response
	        ObjectMapper objectMapper = new ObjectMapper();
	        JsonNode jsonNode = objectMapper.readTree(response);
	        
	        for(int i = 0; i < 10 ; i++)
	        {
	        	JsonNode recipy = jsonNode.get(i); // Assuming it's an array of recipes
	        	
	        	System.out.println("recipy is  : " + recipy);
	        	
	        	db.models.Meal meal = new db.models.Meal();
        		
        		meal.setName(recipy.get("title").asText());
	        	meal.setImage(recipy.get("image").asText());
	        	meal.setTotal_calories(recipy.get("calories").asInt());
	        	meal.setTotal_protein(recipy.get("protein").asInt());
	        	meal.setTotal_fat(recipy.get("fat").asInt());
	        	meal.setTotal_carhbohydrates(recipy.get("carbs").asInt());
	        	
	        	recommendations.add(meal);
	        	
	        	/*
	        	db.models.Meal meal = new db.models.Meal();
	        	
	        	
	        	
	        	System.out.println("recipy : " + recipy);
	        	System.out.println("title : " + recipy.get("title").asText());
	        	
	        	
	        	meal.setName(recipy.get("title").asText());
	        	meal.setImage(recipy.get("image").asText());
	        	
	        	
		        
		        _ingredient.setName(recipy.get("tilte").asText());
		        String recipeName = recipy.get("tilte").asText();
		        System.out.println(recipy);
		        */
	        	
	        }  
	    } catch (IOException e) {
	        // Handle exception
	        e.printStackTrace();
	    }
	    
	    System.out.println(recommendations);
	    
	    return null;
	}
	
	
	
	
}

