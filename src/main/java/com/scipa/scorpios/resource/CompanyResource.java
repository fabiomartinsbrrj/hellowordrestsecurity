package com.scipa.scorpios.resource;

import java.net.URI;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.jboss.logging.Logger;

@Path("companies")
@Produces("application/json")
public class CompanyResource {
	private static final Logger logger = Logger.getLogger(CompanyResource.class);

	@PostConstruct
	private void postConstruct() {
		logger.info("postConstruct chamado");

	}

	@GET
	@Path("{id}")
	@RolesAllowed({ "user_role", "admin_role" })
	public Object getCompany(@PathParam("id") final Long id) {
		logger.info("getCompany chamado");
		return null;
	}

	@POST
	@RolesAllowed({ "admin_role" })
	public Object create() {
		logger.info("create chamado");
		return null;
	}

	@GET
	@RolesAllowed({ "user_role", "admin_role" })
	public List<Object> getAll(@Context final SecurityContext sec) {
		logger.info("getAll chamado");
		//
		logger.info("Id: " + sec.getUserPrincipal().getName());
		String role = "user_role";
		logger.info("Usuario pertence a role? " + role + " : " + sec.isUserInRole(role));
		role = "admin_role";
		logger.info("Usuario pertence a role? " + role + " : " + sec.isUserInRole(role));
		return null;
	}

	@PUT
	@RolesAllowed({ "admin_role" })
	public Object update(final Object selectedCompany) {
		logger.info("update chamado");
		return null;
	}

	@DELETE
	@Path("{id}")
	@RolesAllowed({ "admin_role" })
	public void delete(@PathParam("id") final Long id) {
		logger.info("delete chamado");
	}

	@POST
	@Path("tasks/{title}")
	@RolesAllowed({ "admin_role", "user_role" })
	public Response createTask(@Context final UriInfo info, @Context final SecurityContext context,
			@PathParam("title") @DefaultValue("task") final String taskTitle) {

		final Task task = new Task(taskTitle);
		task.setId(500l);

		final String rawPath = info.getAbsolutePath().getRawPath().replace(task.getTitle(), task.getId().toString());
		final UriBuilder uriBuilder = info.getAbsolutePathBuilder().replacePath(rawPath);
		final URI uri = uriBuilder.build();
		logger.info(uri);
		return Response.created(uri).build();
	}

	@GET
	@Path("tasks/{id}")
	// JSON: include "application/json" in the @Produces annotation to include json support
	@Produces({ "application/xml", "application/json" })
	// @Produces({ "application/xml" })
	public Task getTaskById(@Context final SecurityContext context, @PathParam("id") final Long id) {
		final Task task = new Task("titulo");
		task.setId(id);
		task.setOwner("Queijo");
		task.setNaoLeva("Nao pode aparecer");

		return task;
	}

}
