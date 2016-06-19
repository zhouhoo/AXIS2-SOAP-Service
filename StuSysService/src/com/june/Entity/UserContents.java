package com.june.Entity;



public class UserContents {

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

	public String getWriterName() {
		return writerName;
	}

	public void setWriterName(String writerName) {
		this.writerName = writerName;
	}

	private int contentID;
    
   
	private String title;
    
   
	private String texts;
	
	private String writerName;
}
