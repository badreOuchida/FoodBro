package utils;

import java.io.Serializable;

import db.dao.user.IUser;
import db.dao.user.UserDao;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named
@RequestScoped
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

	
	
	public String submitAction() {
		IUser dao = new UserDao();
		 user  = dao.getUser(1);
		
		return "eng/user.xhtml";
	}
	
	
	public String submitLogout()
	{
		System.out.println("logout");
		return "error.html";
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
	
	
}
