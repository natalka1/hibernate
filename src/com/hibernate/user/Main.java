package com.hibernate.user;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


public class Main {
	
	private static SessionFactory factory;

	public static void main(String[] args) {
		
		try {
	         factory = new Configuration().configure().buildSessionFactory();
	      } catch (Throwable ex) { 
	         System.err.println("Failed to create sessionFactory object." + ex);
	         throw new ExceptionInInitializerError(ex); 
	      }
		
		User user = new User();
		Main main = new Main();
		
		user.setId(1);
		user.setUserId(1);
		user.setTitle("napad1");
		user.setBody("telo mailu");
		
		Integer userId = main.addUser(user);
		
	}
	
	
	public Integer addUser(User user){
	      Session session = factory.openSession();
	      Transaction tx = null;
	      Integer userId = null;
	      
	      try {
	         tx = session.beginTransaction();
	         userId = (Integer) session.save(user); 
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	      return userId;
	   }
}
