package db.dao.ingredient;

import db.conn.DbInteractor;
import db.models.Ingredient;

public class IngredienDao implements IIngredient {
	
	private DbInteractor db ;
	
	public IngredienDao() {
		db = DbInteractor.getInstance();
	}

	@Override
	public int addIngredient(Ingredient ingredient, int meal_id) {
		
		String sql = "INSERT INTO ingredients (meal_id, ingredient_name , calories ) VALUES "
				+ "('"+meal_id+"','"+ingredient.getName()+"','"+ingredient.getCalories()+"')";
		
		int res = db.maj(sql);
		
		System.out.println("end of add ingredient");
		
	
		
		return res;
	}

	@Override
	public int deleteIngredient(int id) {
		System.out.println("delete ingredient");
		return 0;
	}

	@Override
	public Ingredient getIngredient(int id) {
		System.out.println("get ingredient");
		return null;
	}

	@Override
	public int updateIngredient(int id, Ingredient ingredient) {
		System.out.println("update ingredient");
		return 0;
	}

	

}
