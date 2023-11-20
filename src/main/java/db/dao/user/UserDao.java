package db.dao.user;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.mindrot.jbcrypt.BCrypt;

import db.conn.DbInteractor;

import db.models.User;

public class UserDao  implements IUser {
	
	private static String configPath = "C:\\Users\\ouchi\\eclipse-workspace\\FoodBr\\config.properties";
	
	private static String storedSalt()
	{
		Properties properties = new Properties();
		
		// provide FileInputStream with the path for your config.properties file
		
        try (FileInputStream input = new FileInputStream(configPath)) 
        {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        String storedSalt = properties.getProperty("salt");
		return storedSalt;	
	}
	
	private DbInteractor db ;
	
	public UserDao() {
		db = DbInteractor.getInstance();
	}

	
	@Override
	public List<User> getUsers() {
		
		List<User> users = new ArrayList<>();
		
		String sql = "SELECT * FROM users" ;
		
		ResultSet res = db.select(sql);
		
		try {
			while(res.next())
			{
				
				User user = new User();
				
				user.setId(res.getInt(1));
				user.setUsername(res.getString(2));
				user.setFirst_name(res.getString(3));
				user.setLast_name(res.getString(4));
				user.setEmail(res.getString(5));
				user.setStatus(res.getString(7));
				user.setSex(res.getString(8));
				user.setBirthday(res.getDate(9));
				user.setHeight(res.getInt(10));
				user.setWeight(res.getInt(11));
				
				users.add(user);
			}
		} catch (SQLException e) {
			System.out.println("getUsers error");
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public int addUser(User user,String password) {
		
		String hashedPassword = BCrypt.hashpw(password,storedSalt() );
		
		String sql = "INSERT INTO users (id, username, first_name, last_name, email, password ,status, sex, birthday, height, weight)"
				+ "VALUES ('"+user.getUsername()+"','"+user.getFirst_name()+"', '"+user.getLast_name()+"', '"+user.getEmail()+"', '"+hashedPassword+"', '"+user.getStatus()+"', '"+user.getSex()+"', '"+(new java.sql.Date(user.getBirthday().getTime()))+"', "+user.getHeight()+", "+user.getWeight()+");";
		
		int res = db.maj(sql);
		
		return res;
	}

	@Override
	public User getUser(int id) {
		User user = null; 
		String sql = "SELECT * FROM users WHERE user_id = " + id;
		System.out.println("la requete est : " + sql);
		ResultSet res = db.select(sql);
		try {
			if(res.next())
			{
				user = new User();
				
				user.setId(res.getInt(1));
				user.setUsername(res.getString(2));
				user.setFirst_name(res.getString(3));
				user.setLast_name(res.getString(4));
				user.setEmail(res.getString(5));
				user.setStatus(res.getString(7));
				user.setSex(res.getString(8));
				user.setBirthday(res.getDate(9));
				user.setHeight(res.getInt(10));
				user.setWeight(res.getInt(11));
			}
		} catch (SQLException e) {
			System.out.println("getUser error");
			e.printStackTrace();
		}
		return user ;
	}

	@Override
	public int editUser(int id,User user) {
		String sql = "UPDATE users SET username="+user.getUsername()+", first_name="+user.getFirst_name()+", last_name="+user.getLast_name()+", email="+user.getEmail()+", status="+user.getStatus()+", sex="+user.getSex()+", birthday="+(new java.sql.Date(user.getBirthday().getTime()))+", height="+user.getHeight()+", weight="+user.getWeight()+" WHERE id="+user.getId()+"";
		int res = db.maj(sql);
		return res;	
	}

	@Override
	public int deleteUser(int id) {
		String sql = "DELETE FROM users WHERE id = "+id ;
		int res = db.maj(sql);
		return res;
	}


	@Override
	public User getUser(String username,String password) {
		String hashedPassword = BCrypt.hashpw(password,storedSalt() );
		User user = null; 
		String sql = "SELECT * FROM users WHERE username = '" + username + "' AND password = '"+hashedPassword+"'";
		ResultSet res = db.select(sql);
		
		try {
			if(res.next())
			{
				user = new User();
				
				user.setId(res.getInt(1));
				user.setUsername(res.getString(2));
				user.setFirst_name(res.getString(3));
				user.setLast_name(res.getString(4));
				user.setEmail(res.getString(5));
				user.setStatus(res.getString(7));
				user.setSex(res.getString(8));
				user.setBirthday(res.getDate(9));
				user.setHeight(res.getInt(10));
				user.setWeight(res.getInt(11));
				
			}
		} catch (SQLException e) {
			System.out.println("getUser error");
			e.printStackTrace();
		}
		return user ;
	}
	
	public String getTest()
	{
		return "hello world";
	}
}
