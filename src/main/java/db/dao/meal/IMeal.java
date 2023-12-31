package db.dao.meal;

import java.util.List;

import db.models.Meal;

public interface IMeal {
	public int addMeal(Meal meal);
	public int updateMeal(int id,Meal meal);
	public int deleteMeal(int id);
	public Meal getMeal(int user_id, String name);
	public List<Meal> DailyMeal(int user_id);
}
