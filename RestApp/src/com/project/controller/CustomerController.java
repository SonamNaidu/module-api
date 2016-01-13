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

import com.project.dao.CustomerDAO;
import com.project.exception.AppException;
import com.project.model.Customer;

@Path("/customers")
public class CustomerController {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Customer> findAll() {
		List<Customer> customers = null;
		try {
			CustomerDAO dao = new CustomerDAO();
			customers = dao.listCustomers();
		} catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		return customers;
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Customer findCustomer(@PathParam ("id") String id) {
		Customer customer = null;
		try {
			CustomerDAO dao = new CustomerDAO();
			customer = dao.findCustomer(id);
		} catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		return customer;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create (Customer cust) {
		
		try {
			CustomerDAO dao = new CustomerDAO();
			dao.create(cust);
		} catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		return Response.ok().build();
	}
	
	// PUT /api/employees/1020 
	//{}
		
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update (@PathParam("id") int id, Customer cust) {
		
		try {
			CustomerDAO dao = new CustomerDAO();
			dao.update(id, cust);
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
			CustomerDAO dao = new CustomerDAO();
			dao.delete(id);
		} catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		
		return Response.ok().build();
	}
}
