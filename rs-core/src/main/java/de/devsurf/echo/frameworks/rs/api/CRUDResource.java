package de.devsurf.echo.frameworks.rs.api;
//package com.saperion.frameworks.rs.api;
//
//import java.sql.Connection;
//
//import javax.ws.rs.Consumes;
//import javax.ws.rs.DELETE;
//import javax.ws.rs.GET;
//import javax.ws.rs.HEAD;
//import javax.ws.rs.POST;
//import javax.ws.rs.PUT;
//import javax.ws.rs.Path;
//import javax.ws.rs.PathParam;
//import javax.ws.rs.core.Context;
//import javax.ws.rs.core.Response;
//
//public interface CRUDResource<ItemType> {
//	/**
//	 * Indicates if the item with the specific id exists
//	 * 
//	 * @return a 200 ok response is returned if tenants exist. A 404 not found
//	 *         response is returned if not.
//	 */
//	@HEAD
//	@Path("/{id}")
//	@Consumes("*/*")
//	Response exists(@Context Connection connection,
//			@PathParam("id") String itemId) throws BaseException;
//
//	/**
//	 * Used to load an item for the specific id
//	 * 
//	 * @return a 200 ok response is returned if tenants exist. A 404 not found
//	 *         response is returned if not.
//	 */
//	@GET
//	@Path("/{id}")
//	Response get(@Context Connection connection, @PathParam("id") String itemId)
//			throws BaseException;
//
//	@GET
//	Response find(@Context Connection connection) throws BaseException;
//
//	@POST
//	@Consumes("application/json")
//	Response create(@Context Connection connection, ItemType item)
//			throws BaseException;
//
//	@PUT
//	@Path("/{id}")
//	@Consumes("application/json")
//	Response update(@Context Connection connection,
//			@PathParam("id") String itemId, ItemType item) throws BaseException;
//
//	@DELETE
//	@Path("/{id}")
//	Response delete(@Context Connection connection,
//			@PathParam("id") String tenantId) throws BaseException;
//}
