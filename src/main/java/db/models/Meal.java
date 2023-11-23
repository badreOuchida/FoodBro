package db.models;

import java.util.List;

public class Meal {

	private int id;
	private String name;
	private List<Ingredient> ingrediens;
	
	
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
	

}
