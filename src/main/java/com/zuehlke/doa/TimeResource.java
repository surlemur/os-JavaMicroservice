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

@Path("/time")
public class TimeResource {
    
    private Logger log = LoggerFactory.getLogger(TimeResource.class);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String get(@Context HttpHeaders httpHeaders) {
        log.debug("TimeResource was queried with accept=" + httpHeaders.getAcceptableMediaTypes());
        return String.format("{\"time\": \"%s\"}", Instant.now().toString());
    }
}
