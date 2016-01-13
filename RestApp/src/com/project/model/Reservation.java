package com.project.model;

import java.sql.Time;
import java.util.Date;

public class Reservation {
	
	private int confirmationNo;
	private int tableNo;
	private String customerEmailId;
	private Date reservationDate;
	private Time reservationTime;
	private int partySize;
	public int getConfirmationNo() {
		return confirmationNo;
	}
	public void setConfirmationNo(int confirmationNo) {
		this.confirmationNo = confirmationNo;
	}
	public int getTableNo() {
		return tableNo;
	}
	public void setTableNo(int tableNo) {
		this.tableNo = tableNo;
	}
	public String getCustomerEmailId() {
		return customerEmailId;
	}
	public void setCustomerEmailId(String customerEmailId) {
		this.customerEmailId = customerEmailId;
	}
	public Date getReservationDate() {
		return reservationDate;
	}
	public void setReservationDate(Date reservationDate) {
		this.reservationDate = reservationDate;
	}
	public Time getReservationTime() {
		return reservationTime;
	}
	public void setReservationTime(Time reservationTime) {
		this.reservationTime = reservationTime;
	}
	public int getPartySize() {
		return partySize;
	}
	public void setPartySize(int partySize) {
		this.partySize = partySize;
	}
	
	
}
