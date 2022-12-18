package com.server.nanny.boundaries;


import com.server.nanny.security.Oauth2Request;
import com.server.nanny.security.Oauth2Response;
import com.server.nanny.security.Oauth2Service;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@ApplicationScoped
@Path("oauth2")
public class Oauth2Resource {

    @Inject
    private Oauth2Service service;

    @POST
    @Path("token")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Oauth2Response token(@BeanParam @Valid Oauth2Request request) {
        switch (request.getGrandType()) {

            case PASSWORD:
                System.out.println("it is passed  from here");
                return service.token(request);
            case REFRESH_TOKEN:
                return service.refreshToken(request);
            default:
                throw new UnsupportedOperationException("There is not support to another type");
        }
    }
}