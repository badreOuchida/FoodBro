package db.dao.ingredient;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.conn.DbInteractor;
import db.models.Ingredient;
import db.models.User;

public class IngredienDao implements IIngredient {
	
	private DbInteractor db ;
	
	public IngredienDao() {
		db = DbInteractor.getInstance();
	}

	@Override
	public int addIngredient(Ingredient ingredient, int meal_id,int user_id) {
		
		
		String sql = "INSERT INTO ingredients (meal_id, ingredient_name , calories , user_id ) VALUES "
				+ "('"+meal_id+"','"+ingredient.getName()+"','"+ingredient.getCalories()+"','"+user_id+"')";
		
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

	@Override
	public List<Ingredient> getAllIngredient(int user_id) {
		String sql = "SELECT ingredient_name , count(*) FROM ingredients WHERE user_id = "+user_id + " GROUP BY ingredient_name ORDER BY 2 DESC";
		
		List<Ingredient> ingredients = new ArrayList<>();
		
		ResultSet res = db.select(sql);
				
		try {
			while(res.next())
			{
				
				Ingredient ingredient = new Ingredient();
				
				ingredient.setName(res.getString(1));
				
				ingredient.setTimes(res.getInt(2));
				
				ingredients.add(ingredient);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return ingredients;
	}
}
