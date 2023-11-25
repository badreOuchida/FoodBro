package db.dao.meal;

import java.sql.ResultSet;
import java.sql.SQLException;

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
		
		String sql = "INSERT INTO meals (user_id,total_calories,total_protein,total_fat,total_carbohydrates,name) VALUES " + 
				"('"+meal.getUser_id()+"','"+meal.getTotal_calories()+"','"+meal.getTotal_protein()+"','"+meal.getTotal_fat()+"','"+meal.getTotal_carhbohydrates()+"','"+meal.getName()+"')" ;
		int res = db.maj(sql);
		
		// we assume that a user can't give two meals with same name ;
		
		
		Meal _meal = getMeal(meal.getUser_id(),meal.getName());
		
		IIngredient db_ingredient = new IngredienDao();
		
		for( Ingredient ingredient : meal.getIngrediens() )
		{
			System.out.println("inside add meal ingredient est : " + ingredient.getName());
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
				
			}
		} catch (SQLException e) {
			System.out.println("getUser error");
			e.printStackTrace();
		}
		
		System.out.println("my meal inside get " + meal);
		
		return meal;
	}
}
