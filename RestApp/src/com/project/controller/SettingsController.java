package com.project.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import com.project.dao.SettingsDAO;
import com.project.exception.AppException;
import com.project.model.Settings;

@Path("/setting")
public class SettingsController {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Settings getAll() {
		Settings settings = null;
		try {
			SettingsDAO dao = new SettingsDAO();
			settings = dao.getAll();
		} catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		return settings;
	}
	
}
