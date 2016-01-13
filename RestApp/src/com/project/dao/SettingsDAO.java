package com.project.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.project.exception.AppException;
import com.project.model.Settings;
import com.project.util.HibernateUtil;

public class SettingsDAO {

	/* Method to  READ a table based on id */	
	public Settings getAll()  throws AppException{
		Transaction tx = null;
		Settings settings = null;
		Session session = HibernateUtil.getSessionFactory().openSession();		
		try{ 
			tx = session.beginTransaction();
			settings = (Settings) session.createQuery("FROM Settings").list().get(0); 

		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		}finally {
			session.close(); 	
		}
		return settings;
	}
}
