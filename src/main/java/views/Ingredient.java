package views;

import java.io.Serializable;

import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;


@Named
@ViewScoped
public class Ingredient implements Serializable {
	
	private db.models.Ingredient ingredient = new db.models.Ingredient() ;
	
	
	@Inject
	    private Meal meal;
	
	public void addIngredient() {
        
        if (meal != null) {
            meal.addIngredient(ingredient);
        }

        ingredient = new db.models.Ingredient();
    }
	
	public void removeIngredient()
	{
		if(meal != null)
		{
			int nbr = meal.getMeal().getIngrediens().size();
			if( nbr > 0)
			{
				ingredient = meal.getMeal().getIngrediens().get(meal.getMeal().getIngrediens().size() - 1);
				meal.removeIngredient();
			}	
		}
	}
	
	public db.models.Ingredient getIngredient() {
		return ingredient;
	}
	
	public void setIngredient(db.models.Ingredient ingredient) {
		this.ingredient = ingredient;
	}	
}
