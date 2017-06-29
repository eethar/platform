package org.eethar.platform.core.rest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.eethar.platform.app.service.UserService;
import org.eethar.platform.core.repository.mongodb.UserMongoRepository;
import org.eethar.platform.core.service.facade.UserFacade;

/**
 *
 * @author superyass
 */
@Path("/test")
public class TestController {

    @Inject
    UserFacade userFacade;

    @Path("/test1")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String test1() {
        String res = "it works!";

        res = userFacade.count().toString();
        
        return res;
    }

}
