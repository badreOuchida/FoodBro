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
	
	@PostConstruct
    public void init() {
		getDailyCaloris();
    }
	
	
	@Inject
    private User user;
	
	
	
	public String getDailyCaloris()
	{
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
			
			
		}
		total = breakfast + lunch + dinner;
		
		System.out.println("total est : " + total);

		// total == 360
		// calories == x 
		// x = 360 * calories / total;
		calories_percentage = (( 360 * total ) / user.getUser().getCalories());
		
		
		
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
	
	
}



