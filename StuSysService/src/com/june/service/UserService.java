package com.june.service;



import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.june.Entity.Content;
import com.june.Entity.UserContents;
import com.june.Entity.Users;

public class UserService {

	private BaseService<Users> baseUser=new BaseService<Users>();
	private BaseService<Content> baseContent=new BaseService<Content>();
	

	
	public UserService(){
		
	}
	
	
	public int register(String name,String passwd){
		Session session = baseUser.factory.openSession();
		Transaction tx = null;
		Integer useIDSaved = 0;
		try {
			tx = session.beginTransaction();
			Users emp = new Users(name, passwd);
			useIDSaved = (Integer) session.save(emp);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return useIDSaved;
	}
	
	public int login(String name,String passwd){
		Session session = baseUser.factory.openSession();
	

		int userID = 0;
		try {
			
		
			String hql="from Users as u where u.Name =? and u.passwd= ?";
			Users res=baseUser.get(session,hql,new Object[] { name,passwd });
			if(res!=null){
				
				userID=res.getUserID();
				
			
			}
		} catch (HibernateException e) {
			System.out.println(e);
		}finally{
			session.close();
		}
		return userID;
	}
	
	public int updatePasswd(int userID,String oldPasswd,String newPasswd){
		
		
		Session session = baseUser.factory.openSession();
		Transaction tx = null;
		String hql="from Users as u where u.userID= ?";
		Users currentUser=baseUser.get(session,hql,new Object[] { userID });
		int code=0;
		
		
		if(currentUser!=null)
		{
			
			try {
				
				
				if(currentUser.getPasswd().equals(oldPasswd)){
					tx = session.beginTransaction();
					currentUser.setPasswd(newPasswd);
					session.update(currentUser);
					tx.commit();
				}else{
					code=1;//密码错误
				}
				
			} catch (HibernateException e) {
				if (tx != null)
					tx.rollback();
				e.printStackTrace();
			} finally {
				session.close();
			}
			
		}else{
			code=2;//用户不存在
		}
		
		
		return code;
	}

	public Users getUserByID(int userID){
		
		Users currentUser=null;
		Session session = baseUser.factory.openSession();
		String hql="from Users as u where u.userID= ?";
		currentUser=baseUser.get(session,hql,new Object[] { userID });
		
		return currentUser;
	}
	
	public List<Users> getUsers(){
		List<Users> users=null;
		Session session = baseUser.factory.openSession();
		String hql="from Users as u";
		users=baseUser.find(session,hql,null);
		return users;
	}
	
	public int publishContext(int writerID,String title,String contents){
		Session session = baseContent.factory.openSession();
		Transaction tx = null;
		Users user=getUserByID(writerID);
		Integer contentID = 0;
		if(user!=null){
			try {
				tx = session.beginTransaction();
				Content tmp = new Content(title, contents,user);
				contentID = (Integer) session.save(tmp);
				tx.commit();
			} catch (HibernateException e) {
				if (tx != null)
					tx.rollback();
				e.printStackTrace();
			} finally {
				session.close();
			}
		}
		
		return contentID;
	}
	
	public int deleteContent(int writer,int contentID){
		Session session=baseContent.factory.openSession();
		Transaction tx = null;
		int result=1;
		
		try {
			tx = session.beginTransaction();
			String hql="from Content as c where c.user.userID=? and c.contentID=?";
			Content target=baseContent.get(session,hql,new Object[] {writer, contentID});
			if(target!=null){
				
				session.delete(target);
				result=0;//删除成功
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;
		
	}
	
	
	private ArrayList<UserContents> findContents(String hql,Object[] param){
		Session session=baseContent.factory.openSession();
		
		ArrayList<UserContents> data=new ArrayList<UserContents>();
		
		ArrayList<ArrayList<Object>> ls=baseContent.findComposedData(session, hql, param);
		for(int i=0;i<ls.size();i++){
			UserContents tmp=new UserContents();
			ArrayList<Object> obj = ls.get(i);
			tmp.setContentID((int) obj.get(0));
			tmp.setTitle((String) obj.get(1));
			tmp.setTexts((String) obj.get(2));
			tmp.setWriterName((String) obj.get(3));
			
			data.add(tmp);
		}
		return data;
	}
	
	public ArrayList<UserContents> getAllContents(){
		
		
		String hql="select con.contentID,con.title,con.texts,con.user.Name from Content as con order by con.contentID desc";
		ArrayList<UserContents> res=findContents(hql,null);
		
		return res;
		
	}
	
	public ArrayList<UserContents> getContentsByWriter(int writerID){
		
		String hql="select con.contentID,con.title,con.texts,con.user.Name from Content as con where con.user.userID= ? order by con.contentID desc";
		ArrayList<UserContents> res=findContents(hql,new Object[] {writerID});
		
		return res;
	}
	
	
}
