package org.acme;

import java.util.Arrays;
import java.util.HashSet;

import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.jwt.Claims;

import io.smallrye.jwt.build.Jwt;


@Path("/getjwt")
public class JWT {

    @POST
    @PermitAll
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public String getJWT(@FormParam("username") String username, @FormParam("email") String email){
   
        return Jwt.issuer("https://localhost:3340")
            .upn(email)
            .groups(new HashSet<>(Arrays.asList("User")))
            .claim(Claims.full_name, username)
            .claim(Claims.email, email)
            .sign();
    }

}
