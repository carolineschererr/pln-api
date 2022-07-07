package org.acme;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
    
    @Inject
    UserService userService;
    
    @GET
    @Path("/list")
    public Response getAllUsers() {
        return Response.status(Status.OK).entity(userService.getAllUsers()).build();
    }

    @GET
    @Path("/find_by_id/{id}")
    public Response getUserById(@PathParam("id") long id) {
        return Response.status(Status.OK).entity(userService.getUserById(id)).build();
    }

    @GET
    @Path("/find_by_email/{email}")
    public Response getUserByEmail(@PathParam("email") String email) {
        return Response.status(Status.OK).entity(userService.getUserByEmail(email)).build();
    }

    @GET
    @Path("/find_by_username/{username}")
    public Response getUserByUsername(@PathParam("username") String username) {
        return Response.status(Status.OK).entity(userService.getUserByUsername(username)).build();
    }
    
    @POST
    @Path("/register")
    public Response createUser(User user) {
        return Response.status(Status.CREATED).entity(userService.createUser(user)).build();
    }

    @POST
    @Path("/login")
    public Response login(User user) {
        return Response.status(Status.OK).entity(userService.login(user)).build();
    }

    @PUT
    @Path("/update/{id}")
    public Response updateUser(@PathParam("id") Long id, User user) {
        return Response.status(Status.CREATED).entity(userService.updateUser(user)).build();
    }

    @DELETE
    @Path("/delete/{id}")
    public Response deleteUser(@PathParam("id") Long id) {
        return Response.status(Status.OK).entity(userService.deleteUser(id)).build();
    }
}