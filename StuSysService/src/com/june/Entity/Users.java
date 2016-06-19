package com.june.Entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity
public class Users {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	
    @Column(name = "userID")
	private int userID;
    
    @Column(name = "Name")
	private String Name;
    
    @Column(name = "passwd")
	private String passwd;
	
    
    
    public Users(){}

	
	public Users(String Name, String passwd) {
		this.userID = 1;
		this.Name = Name;
		this.passwd = passwd;
	}
	
	public int getUserID() {
		return userID;
	}



	public void setUserID(int userID) {
		this.userID = userID;
	}



	public String getName() {
		return Name;
	}



	public void setName(String name) {
		Name = name;
	}



	public String getPasswd() {
		return passwd;
	}



	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
}
