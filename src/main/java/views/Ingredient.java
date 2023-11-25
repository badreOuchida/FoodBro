package views;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;


@Named
@ViewScoped
public class Ingredient implements Serializable {
	
	private db.models.Ingredient ingredient = new db.models.Ingredient() ;
	
	public db.models.Ingredient getIngredient() {
		return ingredient;
	}
	
	public void setIngredient(db.models.Ingredient ingredient) {
		this.ingredient = ingredient;
	}	
}
