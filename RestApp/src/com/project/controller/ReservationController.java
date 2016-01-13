package com.project.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.project.dao.ReservationDAO;
import com.project.exception.AppException;
import com.project.model.Reservation;

@Path("/reservations")
public class ReservationController {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Reservation> findAll() {
		List<Reservation> reservations = null;
		try {
			ReservationDAO dao = new ReservationDAO();
			reservations = dao.listReservations();
		} catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		return reservations;
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Reservation findReservation(@PathParam ("id") String id) {
		Reservation reservation = null;
		try {
			ReservationDAO dao = new ReservationDAO();
			reservation = dao.findReservation(id);
		} catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		return reservation;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create (Reservation resv) {
		
		try {
			ReservationDAO dao = new ReservationDAO();
			dao.create(resv);
		} catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		return Response.ok().build();
	}
	
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update (@PathParam("id") int id, Reservation resv) {
		
		try {
			ReservationDAO dao = new ReservationDAO();
			dao.update(id, resv);
		} catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		return Response.ok().build();
	}
	
	
	@DELETE
	@Path("/{id}")
	public Response delete (@PathParam("id") int id) {

		try {
			ReservationDAO dao = new ReservationDAO();
			dao.delete(id);
		} catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		
		return Response.ok().build();
	}
}
