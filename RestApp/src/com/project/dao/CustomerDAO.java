package com.project.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.project.exception.AppException;
import com.project.model.Customer;
import com.project.util.HibernateUtil;

public class CustomerDAO {

	/* Method to  READ all the customers */
	@SuppressWarnings("unchecked")
	public List<Customer> listCustomers() throws AppException{
		Transaction tx = null;
		List<Customer> customer = null;		
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{ 
			tx = session.beginTransaction();
			customer = session.createQuery("FROM Customer").list(); 
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		}finally {
			session.close(); 	
		}
		return customer;
	}
 
	/* Method to  READ a customer based on id */	
	public Customer findCustomer(String id)  throws AppException{
		Transaction tx = null;
		Customer customer = null;
		Session session = HibernateUtil.getSessionFactory().openSession();		
		try{ 
			tx = session.beginTransaction();
			customer = session.get(Customer.class, id);

		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		}finally {
			session.close(); 	
		}
		return customer;
	}

	/* Method to  CREATES a customer */	
	public void create(Customer cust)  throws AppException{
		Transaction txn = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			txn = session.beginTransaction();
			session.save(cust);
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			if (txn != null) {
				txn.rollback();
			}
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}

	}

	/* Method to  UPDATES a customer */	
	public void update(int id, Customer cust)  throws AppException{
		Transaction txn = null;
		Customer customer;
		Session session = HibernateUtil.getSessionFactory().openSession();		
		try {
			txn = session.beginTransaction();
			customer = session.get(Customer.class, id);
			customer.setCustomerName(cust.getCustomerName());
			customer.setCustomerPhone(cust.getCustomerPhone());
			session.update(customer);
		} catch (RuntimeException e) {
			if (txn != null) {
				txn.rollback();
			}
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
	}

	/* Method to  DELETES a customer */	
	public void delete(int id)  throws AppException{
		Transaction txn = null;
		Session session = HibernateUtil.getSessionFactory().openSession();

		try {
			txn = session.beginTransaction();
			Customer cust = (Customer) session.load(Customer.class, new Integer(id));
			session.delete(cust);
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			if (txn != null) {
				txn.rollback();
			}
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}

	}
}
