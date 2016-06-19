package com.june.service;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import com.june.Entity.*;




public  class BaseService<T> {

	public  SessionFactory factory;
	public  ServiceRegistry serviceRegistry;
    
    public  Configuration configuration = new Configuration();
	
	public BaseService( ) {
		
		configuration.configure();
//		configuration.addAnnotatedClass(this.typeParameterClass);
		configuration.addAnnotatedClass(Content.class);
		configuration.addAnnotatedClass(Users.class);
		serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
				configuration.getProperties()).build();
		
		this.factory = configuration.buildSessionFactory(serviceRegistry);
	}
	
	@SuppressWarnings("unchecked")
	
	public List<T> find(Session session,String hql, Object[] param) {
			
		Query q = session.createQuery(hql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i, param[i]);
			}
		}
		
		return q.list();

	}
	
	@SuppressWarnings("rawtypes")
	public ArrayList<ArrayList<Object>> findComposedData(Session session,String hql,
			Object[]  params) {
		ArrayList<ArrayList<Object>> al = new ArrayList<ArrayList<Object> >();
		Query sq = session.createQuery(hql);
		if(params!=null){
			for (int i = 0; i < params.length; i++)
				sq.setParameter(i, params[i]);
		}
		
		List list = sq.list();
		
		for (int i = 0; i < list.size(); i++) {
			ArrayList<Object> map = new ArrayList<Object>();
			Object[] obj = (Object[]) list.get(i);
			// obj中保存的是查询出的对象的属性值
			for (int j = 0; j < obj.length; j++) {
				// 循环打印Dept的属性值
				map.add(obj[j]);
			}
			al.add(map);
		}
		return al;
	}
	
	public T get(Session session,String hql, Object[] param){
		List<T> list=find(session,hql,param);
		T target=null;
		if(list!=null&& list.size()>0)
		{
			target=list.get(0);
		}
		
		return target;
	}
	
}
