package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.util.ArrayList;

import db.dao.meal.IMeal;
import db.dao.meal.MealDao;
import db.models.Ingredient;
import db.models.Meal;

/**
 * Servlet implementation class AddMeal
 */
public class AddMeal extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddMeal() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		int userId = Integer.parseInt(request.getParameter("id"));
		
        String name = request.getParameter("name");
        String mealType = request.getParameter("type");
        int calories = Integer.parseInt(request.getParameter("calories"));
        int protein = Integer.parseInt(request.getParameter("protein"));
        int fat =  Integer.parseInt(request.getParameter("fat")); 
        int carbs = Integer.parseInt(request.getParameter("carbs")); 
        
        Meal meal = new Meal();
		
		meal.setName(name);
		meal.setMeal_type(mealType);
		meal.setTotal_calories(calories);
		meal.setTotal_carhbohydrates(carbs);
		meal.setTotal_fat(fat);
		meal.setTotal_protein(protein);
		meal.setUser_id(userId);
		
		// we can compute the daily contrast meals here 
		
		meal.setIngrediens(new ArrayList<Ingredient>());
		IMeal mealdao = new MealDao();
		mealdao.addMeal(meal);
		
		response.sendRedirect("/JSFjack/eng/user/dashbord.xhtml");
		
	}

}
