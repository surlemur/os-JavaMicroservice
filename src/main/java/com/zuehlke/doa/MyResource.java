package com.zuehlke.doa;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("myResource")
public class MyResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String get() {
        return "My Resource!";
    }
}
