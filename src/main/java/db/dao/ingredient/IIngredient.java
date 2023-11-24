package db.dao.ingredient;

import db.models.Ingredient;

public interface IIngredient {
	public int addIngredient(Ingredient ingredient , int meal_id); 
	public int deleteIngredient(int id);
	public Ingredient getIngredient(int id);
	public int updateIngredient(int id,Ingredient ingredient); // new_ingredient
}
