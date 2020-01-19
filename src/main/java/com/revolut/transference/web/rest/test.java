package com.revolut.transference.web.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;


@Path("/test")
public class test {

    @GET
    public Response test(){
      return Response.ok("klok").build();
    }
}
