package views;

import java.io.Serializable;
import java.util.List;

import db.dao.meal.IMeal;
import db.dao.meal.MealDao;
import db.dao.user.IUser;
import db.dao.user.UserDao;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;
import utils.NutritionCalculator;




@Named
@SessionScoped
public class User implements Serializable {
	
	
	
	

	private db.models.User user;
	
	private String email ; 
	private String login ; 
	private String password ; 
	
	
	@PostConstruct
    public void init() {
        // Initialize the user property
        user = new db.models.User();
    }
	
	
	
	
	public db.models.User getUser()
	{
		return user;
	}

	
	public void setUser(db.models.User user)
	{
		this.user = user;
	}
	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public String getLogin() {
		return login;
	}

	
	
	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String submitRegisterHomePage()
	{
		if( user.getUsername() != null && user.getEmail() != null && password != null )
		{
			return "/eng/register.xhtml";
		}
		return "/eng/home.xhtml";
	}
	
	public String submitRegister()
	{
		
		if (user == null || user.getUsername() == null || user.getFirst_name() == null || user.getLast_name() == null ||
			    user.getEmail() == null || user.getStatus() == null || user.getSex() == null ||
			    user.getBirthday() == null || user.getCity() == null || user.getCountry() == null ||
			    user.getGoal() == null)
		{
			return "/eng/home.xhtml";
		}else {
			IUser userDao = new UserDao();
			
			// calculate the nutrition factors
			NutritionCalculator.calculateNutrition(user.getHeight(), user.getHeight(), user.getGoalWeight(), Integer.parseInt(user.getStatus()));
	           
            user.setCalories(NutritionCalculator.getCalories());
            user.setProtein(NutritionCalculator.getProtein());
            user.setCarbs(NutritionCalculator.getCarbs());
            user.setFats(NutritionCalculator.getFats());
            
           
			userDao.addUser(user, password);
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            session.setAttribute("user", user);
            password = null ; // for security purposes
            
            
            
			return "/eng/user/dashbord.xhtml";
		}
		
		
	}
	
	
	
	public String submitLogin() {
        IUser userDao = new UserDao();
        
        
        user = userDao.getUser(user.getUsername(),password);
        
        if (user != null) {
        	HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            session.setAttribute("user", user);
        	// Redirect to a secure page
            password = null ; // for security purposes 
            return "/eng/user/dashbord.xhtml?faces-redirect=true";
        } else {
            return "/eng/home.xhtml"; // Stay on the same page
        }
    }
	
	public String submitLogout()
	{
		FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        externalContext.invalidateSession();
		return "/eng/home.xhtml";
	}
	
	
	public String submitEdit()
	{
		// calculate the nutrition factors
		NutritionCalculator.calculateNutrition(user.getHeight(), user.getHeight(), user.getGoalWeight(), Integer.parseInt(user.getStatus()));
			           
		user.setCalories(NutritionCalculator.getCalories());
		user.setProtein(NutritionCalculator.getProtein());
		user.setCarbs(NutritionCalculator.getCarbs());
		user.setFats(NutritionCalculator.getFats());
		
		IUser userDao = new UserDao();
        
		userDao.editUser(user.getId(), user);
		
		return "/eng/user/user.xhtml?faces-redirect=true";
	}
	
	

	
	
}
