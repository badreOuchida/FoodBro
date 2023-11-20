package db.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class User {
	private int id ;
	private String username;
	private String first_name ; 
	private String last_name ; 
	private String email ;
	private String status;
	private String sex;
	private String birthday;
	private int height;
	private int weight;
	
	@Override
	public String toString() {
		return "User [username=" + username + ", first_name=" + first_name + ", last_name=" + last_name + ", birtday=" + birthday + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	
	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}
	
	private static Date date_convertor(String birthday)
	{
		SimpleDateFormat input_format = new SimpleDateFormat("yyyy-MM-dd");
        
		try {
			Date date;
			date = input_format.parse(birthday);

	        Calendar calendar = Calendar.getInstance();
	        calendar.setTime(date);

	        int year = calendar.get(Calendar.YEAR);
	        int month = calendar.get(Calendar.MONTH) + 1; // Months are zero-indexed
	        int day = calendar.get(Calendar.DAY_OF_MONTH);

	        Date newDate = new Date(year, month - 1, day); // Months are zero-indexed in Date constructor
	        System.out.println(newDate); // Output: Tue Mar 26 00:00:00 PST 2001
			return newDate;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		return null;
	}

	public void setBirthday(String birthday) {
		this.birthday =birthday;
		
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
}
