package com.june.Entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Content {




	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "contentID")
	private int contentID;
    
    @Column(name = "title")
	private String title;
    
    @Column(name = "texts")
	private String texts;
    
    
   
  //  @Column(name = "userID")
//	private int userID;
    
   
    @ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE},targetEntity=Users.class)
    @JoinColumn(name="writerID")
    private Users user;
    

    public Content(){}
    

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Content(String title, String words,Users writer) {
		this.contentID = 1;
		this.title=title;
		this.texts = words;
		this.user = writer;
	}
    
	public int getContentID() {
		return contentID;
	}



	public void setContentID(int contentID) {
		this.contentID = contentID;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getTexts() {
		return texts;
	}



	public void setTexts(String texts) {
		this.texts = texts;
	}



/*	public int getUserID() {
		return userID;
	}



	public void setUserID(int userID) {
		this.userID = userID;
	}
*/
}
