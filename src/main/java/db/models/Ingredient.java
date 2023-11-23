package db.models;

public class Ingredient {
	
	private int id ;
	private String name ; 
	private int quantity;
	private float calories; 
	private float totalCalories;
	
	
	
	
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
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public float getCalories() {
		return calories;
	}
	public void setCalories(float calories) {
		this.calories = calories;
	}
	public float getTotalCalories() {
		return totalCalories;
	}
	public void setTotalCalories(float totalCalories) {
		this.totalCalories = totalCalories;
	}
	
	

}
