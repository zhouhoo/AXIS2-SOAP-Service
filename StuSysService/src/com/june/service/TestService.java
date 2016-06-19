package com.june.service;

import java.util.ArrayList;
import java.util.List;

import com.june.Entity.Content;
import com.june.Entity.UserContents;

public class TestService {

	public UserService service=new UserService();
	public static void main(String[] args) {
		
		TestService s=new TestService();
		
		ArrayList<UserContents> res=s.service.getAllContents();
		
		UserContents ua=res.get(0);
		
		System.out.println(ua);
		System.out.println(ua.getWriterName());
		
	}
		
		
	}



