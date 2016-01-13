package com.project.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import com.project.dao.OwnerDAO;
import com.project.model.OwnerDetails;

@Path("/owner")
public class OwnerController {

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public OwnerDetails findOwner(@PathParam ("id") String id) {
		OwnerDetails owner = null;
		try {
			OwnerDAO dao = new OwnerDAO();
			owner = dao.findOwner(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		return owner;
	}
}
