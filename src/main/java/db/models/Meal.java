package db.models;

import java.sql.Date;
import java.util.List;

public class Meal {

	private String image ;
	
	private int id;
	private int  user_id;
	private String name;
	private Date meal_date;
	private int total_calories;
	private int total_fat = 50;
	private int total_protein = 50;
	private int total_carhbohydrates = 50;
	
	private List<Ingredient> ingrediens;
	
	
	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public Date getMeal_date() {
		return meal_date;
	}

	public void setMeal_date(Date date) {
		this.meal_date = date;
	}

	public int getTotal_calories() {
		return total_calories;
	}

	public void setTotal_calories(int total_calories) {
		this.total_calories = total_calories;
	}

	public int getTotal_fat() {
		return total_fat;
	}

	public void setTotal_fat(int total_fat) {
		this.total_fat = total_fat;
	}

	public int getTotal_protein() {
		return total_protein;
	}

	public void setTotal_protein(int total_protein) {
		this.total_protein = total_protein;
	}

	public int getTotal_carhbohydrates() {
		return total_carhbohydrates;
	}

	public void setTotal_carhbohydrates(int total_carhbohydrates) {
		this.total_carhbohydrates = total_carhbohydrates;
	}
	
	public int getId()
	{
		return this.id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Ingredient> getIngrediens() {
		return ingrediens;
	}
	public void setIngrediens(List<Ingredient> ingrediens) {
		this.ingrediens = ingrediens;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	

}
