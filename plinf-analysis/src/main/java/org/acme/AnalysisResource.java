package org.acme;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("/analysis")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AnalysisResource {
    
    @Inject
    AnalysisService analysisService;
    
    @GET
    @Path("/list")
    public Response getAllAnalysis() {
        return Response.status(Status.OK).entity(analysisService.getAllAnalysis()).build();
    }

    @GET
    @Path("/find_by_id/{id}")
    public Response getUserById(@PathParam("id") long id) {
        return Response.status(Status.OK).entity(analysisService.getAnalysisById(id)).build();
    }
    
    @POST
    @Path("/send_text")
    public Response createAnalysis(Analysis analysis) {
        return Response.status(Status.CREATED).entity(analysisService.createAnalysis(analysis)).build();
    }

    @DELETE
    @Path("/delete/{id}")
    public Response deleteAnalysis(@PathParam("id") Long id) {
        return Response.status(Status.OK).entity(analysisService.deleteAnalysis(id)).build();
    }
}