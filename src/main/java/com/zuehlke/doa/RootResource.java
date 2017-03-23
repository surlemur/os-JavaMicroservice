package com.zuehlke.doa;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/")
public class RootResource {

    private Logger log = LoggerFactory.getLogger(RootResource.class);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String get(@Context HttpHeaders httpHeaders) {
        log.debug("RootResource was queried with accept=" + httpHeaders.getAcceptableMediaTypes());
        return "{\"message\": \"Hello world!\"}";
    }
}
