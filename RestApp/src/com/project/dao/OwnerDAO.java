package com.project.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.project.exception.AppException;
import com.project.model.OwnerDetails;
import com.project.util.HibernateUtil;

public class OwnerDAO {

	/* Method to  READ a table based on id */	
	public OwnerDetails findOwner(String id) throws AppException{
		Transaction tx = null;
		OwnerDetails owner = null;
		Session session = HibernateUtil.getSessionFactory().openSession();		
		try{ 
			tx = session.beginTransaction();
			owner = session.get(OwnerDetails.class, id);

		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		}finally {
			session.close(); 	
		}
		return owner;
	}
}
