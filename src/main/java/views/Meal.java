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
	
	public String getRecommendations() {
        
	    return null;
	}
}
