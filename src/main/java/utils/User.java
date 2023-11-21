package utils;

import java.io.Serializable;

import db.dao.user.IUser;
import db.dao.user.UserDao;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;


@Named
@SessionScoped
public class User implements Serializable {
	
	
	private String nom = "Ouchida";
	private String prenom = "Badre";
	private String mail = "Ouchida2badre@gmail.com";
	private String phoneNumber = "0678488570";
	
	private db.models.User user;
	
	public db.models.User getUser()
	{
		return user;
	}

	
	private String login ; 
	private String password ; 
	
	
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

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String number) {
		this.phoneNumber = number;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	
	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public int getRemainingDates()
	{
		return 10;
	}
	
	
	
	public String submitLogin() {
        IUser userDao = new UserDao();
        
        
        user = userDao.getUser(login,password);
        
        if (user != null) {
        	HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            session.setAttribute("user", user);
        	// Redirect to a secure page
            System.out.println("I'm logged in bro");
            return "/eng/user/dashbord.xhtml?faces-redirect=true";
        } else {
            return "login.xhtml"; // Stay on the same page
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
		IUser userDao = new UserDao();
        
		userDao.editUser(user.getId(), user);
		
		return "/eng/user/user.xhtml?faces-redirect=true";
	}
	
}
