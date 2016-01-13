package com.project.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.project.model.Reservation;
import com.project.model.Settings;
import com.project.util.HibernateUtil;

public class ReservationDAO {

	/* Method to  READ all the reservations */
	@SuppressWarnings("unchecked")
	public List<Reservation> listReservations(){
		Transaction tx = null;
		List<Reservation> reservation = null;		
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{ 
			tx = session.beginTransaction();
			reservation = session.createQuery("FROM Reservation").list(); 
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		}finally {
			session.close(); 	
		}
		return reservation;
	}
 
	/* Method to  READ a reservation based on id */	
	public Reservation findReservation(String id){
		Transaction tx = null;
		Reservation reservation = null;
		Session session = HibernateUtil.getSessionFactory().openSession();		
		try{ 
			tx = session.beginTransaction();
			reservation = session.get(Reservation.class, id);

		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		}finally {
			session.close(); 	
		}
		return reservation;
	}

	/* Method to  CREATES a reservation */	
	public void create(Reservation resv) {
		Transaction txn = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			txn = session.beginTransaction();
			session.save(resv);
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

	/* Method to  UPDATES a reservation */	
	public void update(int id, Reservation resv) {
		Transaction txn = null;
		Reservation reservation;
		TableDAO table = new TableDAO();
		Session session = HibernateUtil.getSessionFactory().openSession();		
		try {
			txn = session.beginTransaction();
			reservation = session.get(Reservation.class, id);
			reservation.setPartySize(resv.getPartySize());
			reservation.setReservationTime(resv.getReservationTime());
			reservation.setReservationDate(resv.getReservationDate());
			
			Settings settings = (Settings) session.createQuery("FROM Settings").list().get(0);
			if(settings.getAutoTableAssign() ==1){
				resv.setTableNo(table.getAvailableTables(resv.getPartySize()).get(0).getTableNo());
			}
			if(resv.getTableNo() != 0){
				reservation.setTableNo(resv.getTableNo());
			}
			
			session.update(reservation);
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

	/* Method to  DELETES a reservation */	
	public void delete(int id) {
		Transaction txn = null;
		Session session = HibernateUtil.getSessionFactory().openSession();

		try {
			txn = session.beginTransaction();
			Reservation resv = (Reservation) session.load(Reservation.class, new Integer(id));
			session.delete(resv);
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
