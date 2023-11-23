package views;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;


@Named
@ViewScoped
public class Meal implements Serializable {
	
	
	private db.models.Meal meal ;
	
	@PostConstruct
    public void init() {
        // Initialize the user property
        meal = new db.models.Meal();
        meal.setIngrediens(new ArrayList<>());
    }
	

	public db.models.Meal getMeal() {
		return meal;
	}

	public void setMeal(db.models.Meal meal) {
		this.meal = meal;
	}
	
	public void addIngredient(db.models.Ingredient ingredient)
	{
		this.meal.getIngrediens().add(ingredient);	
	}
	
	public void removeIngredient()
	{
		this.meal.getIngrediens().remove(this.meal.getIngrediens().size() - 1);
	}
	
}
