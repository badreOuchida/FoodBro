package db.dao.meal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.conn.DbInteractor;
import db.dao.ingredient.IIngredient;
import db.dao.ingredient.IngredienDao;
import db.models.Ingredient;
import db.models.Meal;
import db.models.User;

public class MealDao implements IMeal {

	private DbInteractor db ;
	
	public MealDao() {
		db = DbInteractor.getInstance();
	}
	@Override
	public int addMeal(Meal meal) {
		
		String sql = "INSERT INTO meals (user_id,total_calories,total_protein,total_fat,total_carbohydrates,name , meal_type) VALUES " + 
				"('"+meal.getUser_id()+"','"+meal.getTotal_calories()+"','"+meal.getTotal_protein()+"','"+meal.getTotal_fat()+"','"+meal.getTotal_carhbohydrates()+"','"+meal.getName()+"','"+meal.getMeal_type()+"')" ;
		int res = db.maj(sql);
		
		// we assume that a user can't give two meals with same name ;
		
		
		Meal _meal = getMeal(meal.getUser_id(),meal.getName());
		
		IIngredient db_ingredient = new IngredienDao();
		
		for( Ingredient ingredient : meal.getIngrediens() )
		{
			db_ingredient.addIngredient(ingredient , _meal.getId() , _meal.getUser_id());
		}
		
		return res;
	}

	@Override
	public int updateMeal(int id, Meal meal) {
		System.out.println("update meal");
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteMeal(int id) {
		System.out.println("delete meal");
		// TODO Auto-generated method stub
		return 0;
	}

	
	@Override
	public Meal getMeal(int user_id, String name) {
		
		String sql = "SELECT * FROM meals WHERE user_id= " + user_id + " AND " + "name=" + "'"+name+"'";
		
		ResultSet res = db.select(sql);
		Meal meal = null;
		
		try {
			if(res.next())
			{
				
				meal = new Meal();
				meal.setId(res.getInt(1));
				meal.setUser_id(res.getInt(2));
				meal.setMeal_date(res.getDate(3));
				meal.setTotal_calories(res.getInt(4));
				meal.setTotal_protein(res.getInt(5));
				meal.setTotal_fat(res.getInt(6));
				meal.setTotal_carhbohydrates(res.getInt(7));
				meal.setTotal_carhbohydrates(50);
				meal.setName(name);
				meal.setMeal_type(res.getString(9));
				
			}
		} catch (SQLException e) {
			System.out.println("getUser error");
			e.printStackTrace();
		}
		
		
		
		return meal;
	}
	@Override
	public List<Meal> DailyMeal(int user_id) {
		String sql = "SELECT * FROM meals WHERE DATE(meal_date) = CURRENT_DATE AND user_id = " + user_id; 
		ResultSet res = db.select(sql);
		
		List<Meal> meals = new ArrayList<>();
		
		try {
			while(res.next())
			{
				Meal meal = null;
				
				meal = new Meal();
				meal.setId(res.getInt(1));
				meal.setUser_id(res.getInt(2));
				meal.setMeal_date(res.getDate(3));
				meal.setTotal_calories(res.getInt(4));
				meal.setTotal_protein(res.getInt(5));
				meal.setTotal_fat(res.getInt(6));
				meal.setTotal_carhbohydrates(res.getInt(7));
				meal.setName(res.getString(8));
				meal.setMeal_type(res.getString(9));
				
				meals.add(meal);
				
			}
		} catch (SQLException e) {
			System.out.println("getUser error");
			e.printStackTrace();
		}
		

		
		return meals;
	}
}
