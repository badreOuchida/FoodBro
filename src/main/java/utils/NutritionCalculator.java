package utils;

import java.text.DecimalFormat;

public class NutritionCalculator {
	
	private static double bmr; // Basal Metabolic Rate
    private static double calories;
    private static double protein;
    private static double carbs;
    private static double fats;
    
    public static void calculateNutrition(double height, double weight, double goalWeight, int activityLevel) {
       

        // Calculate BMR
        if (goalWeight < weight) {
            bmr = 10 * weight + 6.25 * height - 5 * (weight - goalWeight) + 5;
        } else {
            bmr = 10 * weight + 6.25 * height - 5 * (weight - goalWeight) - 161;
        }

      
      
        // Adjust BMR based on activity level
        calories = bmr * getActivityMultiplier(activityLevel);
        protein = 0.2 * calories / 4; // 20% of total calories, assuming 4 calories per gram of protein
        carbs = 0.5 * calories / 4;   // 50% of total calories, assuming 4 calories per gram of carbohydrates
        fats = 0.3 * calories / 9;    // 30% of total calories, assuming 9 calories per gram of fat
        
        DecimalFormat df = new DecimalFormat("#.##");
        
        
        // for precision 
        setCalories(Double.parseDouble(df.format(calories).replace(",", ".")));
        setProtein(Double.parseDouble(df.format(protein).replace(",", ".")));
        setCarbs(Double.parseDouble(df.format(carbs).replace(",", ".")));
        setFats(Double.parseDouble(df.format(fats).replace(",", ".")));        
    }

    private static double getActivityMultiplier(int activityLevel) {
        switch (activityLevel) {
            case 1:
                return 1.2; // Sedentary
            case 2:
                return 1.375; // Lightly active
            case 3:
                return 1.55; // Moderately active
            case 4:
                return 1.725; // Very active
            case 5:
                return 1.9; // Extremely active
            default:
                return 1.0; // Default to sedentary
        }
    }

	public static double getBmr() {
		return bmr;
	}

	public static void setBmr(double bmr) {
		NutritionCalculator.bmr = bmr;
	}

	public static double getCalories() {
		return calories;
	}

	public static void setCalories(double calories) {
		NutritionCalculator.calories = calories;
	}

	public static double getProtein() {
		return protein;
	}

	public static void setProtein(double protein) {
		NutritionCalculator.protein = protein;
	}

	public static double getCarbs() {
		return carbs;
	}

	public static void setCarbs(double carbs) {
		NutritionCalculator.carbs = carbs;
	}

	public static double getFats() {
		return fats;
	}

	public static void setFats(double fats) {
		NutritionCalculator.fats = fats;
	}
    
    
    
    
}
