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

import com.project.dao.TableDAO;
import com.project.exception.AppException;
import com.project.model.Table;

@Path("/tables")
public class TableController {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Table> findAll() {
		List<Table> tables = null;
		try {
			TableDAO dao = new TableDAO();
			tables = dao.listTables();
		} catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		return tables;
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Table findTable(@PathParam ("id") String id) {
		Table table = null;
		try {
			TableDAO dao = new TableDAO();
			table = dao.findTable(id);
		} catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		return table;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create (Table table) {

		try {
			TableDAO dao = new TableDAO();
			dao.create(table);
		} catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		return Response.ok().build();
	}

	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update (@PathParam("id") int id, Table table) {

		try {
			TableDAO dao = new TableDAO();
			dao.update(id, table);
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
			TableDAO dao = new TableDAO();
			dao.delete(id);
		} catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}

		return Response.ok().build();
	}

	@GET
	@Path("/available/{size}")
	@Produces(MediaType.APPLICATION_JSON)		
	public List<Table> getAvailableTables (@PathParam("size") int size) {
		List<Table> tables = null;
		try {
			TableDAO dao = new TableDAO();
			tables = dao.getAvailableTables(size);
		} catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}

		return tables;
	}
}
