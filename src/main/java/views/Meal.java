package views;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
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
		
Properties properties = new Properties();
		
		// provide FileInputStream with the path for your config.properties file
		
        try (FileInputStream input = new FileInputStream("C:\\Users\\ouchi\\eclipse-workspace\\FoodBr\\config.properties")) 
        {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        String apiKey = properties.getProperty("apiKey");
        	
		String response = null;
		
		String apiEndpoint = "https://api.spoonacular.com/recipes/findByNutrients?apiKey="+apiKey+"&query&";
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
	        	db.models.Meal meal = new db.models.Meal();
        		
        		meal.setName(recipy.get("title").asText());
	        	meal.setImage(recipy.get("image").asText());
	        	meal.setTotal_calories(recipy.get("calories").asInt());
	        	String protein = recipy.get("protein").asText();
	        	meal.setTotal_protein(Integer.parseInt(protein.substring(0,protein.length()-1)));
	        	String fat = recipy.get("fat").asText();
	        	meal.setTotal_fat(Integer.parseInt(fat.substring(0,fat.length()-1)));
	        	
	        	String carbs = recipy.get("carbs").asText();
	        	meal.setTotal_carhbohydrates(Integer.parseInt(carbs.substring(0,carbs.length()-1)));
	        	
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
	    
	    

	    return null;
	}
	
	
	
	
}

