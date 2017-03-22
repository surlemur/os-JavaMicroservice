package com.zuehlke.doa;

import java.time.Instant;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("myResource")
public class MyResource {
    
    private Logger log = LoggerFactory.getLogger(MyResource.class);

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String get(@Context HttpHeaders httpHeaders) {
        log.debug("MyResource was queried with accept=" + httpHeaders.getAcceptableMediaTypes());
        return "My Resource - it is now " + Instant.now();
    }
}
