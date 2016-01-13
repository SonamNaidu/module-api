package com.project.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.project.model.Table;
import com.project.util.HibernateUtil;

public class TableDAO {

	/* Method to  READ all the tables */
	@SuppressWarnings("unchecked")
	public List<Table> listTables(){
		Transaction tx = null;
		List<Table> table = null;		
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{ 
			tx = session.beginTransaction();
			table = session.createQuery("FROM Table").list(); 
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		}finally {
			session.close(); 	
		}
		return table;
	}
 
	/* Method to  READ a table based on id */	
	public Table findTable(String id){
		Transaction tx = null;
		Table table = null;
		Session session = HibernateUtil.getSessionFactory().openSession();		
		try{ 
			tx = session.beginTransaction();
			table = session.get(Table.class, id);

		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		}finally {
			session.close(); 	
		}
		return table;
	}

	/* Method to  CREATES a table */	
	public void create(Table table) {
		Transaction txn = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			txn = session.beginTransaction();
			session.save(table);
			session.getTransaction().commit();
			System.out.println("after");
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

	/* Method to  UPDATES a table */	
	public void update(int id, Table tbl) {
		Transaction txn = null;
		Table table;
		Session session = HibernateUtil.getSessionFactory().openSession();		
		try {
			txn = session.beginTransaction();
			table = session.get(Table.class, id);
			table.setTableStatus(tbl.getTableStatus());
			session.update(table);
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

	/* Method to  DELETES a table */	
	public void delete(int id) {
		Transaction txn = null;
		Session session = HibernateUtil.getSessionFactory().openSession();

		try {
			txn = session.beginTransaction();
			Table table = (Table) session.get(Table.class, new Integer(id));
			session.delete(table);
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
	
	@SuppressWarnings("unchecked")
	public List<Table> getAvailableTables(int size) {
		List<Table> availabletables = null;
        Transaction txn = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            txn = session.beginTransaction();
            String queryString = "from Table where tableStatus = :tableStatus and tableSize >= :tableSize order by tableSize";
            Query query = session.createQuery(queryString);
            query.setInteger("tableStatus", 0);
            query.setInteger("tableSize", size);
            availabletables = query.list();
        } catch (RuntimeException e) {
			if (txn != null) {
				txn.rollback();
			}
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
        return availabletables;
    }
}
