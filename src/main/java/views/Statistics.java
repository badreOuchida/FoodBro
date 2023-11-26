package views;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import db.dao.meal.IMeal;
import db.dao.meal.MealDao;
import jakarta.annotation.PostConstruct;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;


@Named
@ViewScoped
public class Statistics implements Serializable {
	private double breakfast ;
	private double lunch ;
	private double dinner;
	private double total;
	private double calories_percentage ;
	private double protein_percentage;
	private double carbs_percentage;
	private double fats_percentage;
	
	@PostConstruct
    public void init() {
		getDailyCaloris();
    }
	
	
	@Inject
    private User user;
	
	
	
	public String getDailyCaloris()
	{
		int carbs = 0;
		int fats = 0;
		int protein = 0;
		System.out.println("hello bro");
		IMeal meal = new MealDao();
		List<db.models.Meal> meals = meal.DailyMeal(user.getUser().getId());
		
		System.out.println("user id is : " + user.getUser().getId());
		
		for(db.models.Meal m :meals)
		{
			
			switch (m.getMeal_type()) {
            case "breakfast" :
            	breakfast += m.getTotal_calories();
                break;
            case "lunch":
            	lunch +=  m.getTotal_calories();
            	
                break;
            case "dinner":
            	dinner += m.getTotal_calories();
            	
                break;
            default:
            	lunch +=  m.getTotal_calories();
			}
			
			carbs += m.getTotal_carhbohydrates();
			fats += m.getTotal_fat();
			protein += m.getTotal_protein();
			
			
		}
		total = breakfast + lunch + dinner;
		
		// total == 360
		// calories == x 
		// x = 360 * calories / total;
		calories_percentage = (( 360 * total ) / user.getUser().getCalories());
		
		// protein == 100
		// eaten_pro == x
		protein_percentage = ( 100 * protein ) / user.getUser().getProtein();
		fats_percentage = ( 100 * fats ) / user.getUser().getFats();	
		carbs_percentage = (100* carbs) / user.getUser().getCarbs();
		
		
		return null;
	}	
	
	public double getTotal() {
		return total;
	}



	public void setTotal(double total) {
		this.total = total;
	}
	
	public double getBreakfast() {
		return breakfast;
	}

	public void setBreakfast(double breakfast) {
		this.breakfast = breakfast;
	}

	public double getLunch() {
		return lunch;
	}

	public void setLunch(double lunch) {
		this.lunch = lunch;
	}

	public double getDinner() {
		return dinner;
	}

	public void setDinner(double dinner) {
		this.dinner = dinner;
	}
	
	public double getCalories_percentage() {
		return calories_percentage;
	}



	public void setCalories_percentage(double calories_percentage) {
		this.calories_percentage = calories_percentage;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public double getProtein_percentage() {
		return protein_percentage;
	}

	public void setProtein_percentage(double protein_percentage) {
		this.protein_percentage = protein_percentage;
	}

	public double getCarbs_percentage() {
		return carbs_percentage;
	}

	public void setCarbs_percentage(double carbs_percentage) {
		this.carbs_percentage = carbs_percentage;
	}

	public double getFats_percentage() {
		return fats_percentage;
	}

	public void setFats_percentage(double fats_percentage) {
		this.fats_percentage = fats_percentage;
	}
	
	
	
}



