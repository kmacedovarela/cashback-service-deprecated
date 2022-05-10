package org.acme.customer;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Logger;

@Path("customer")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class CustomerCustomResource {

    private static final Logger LOGGER = Logger.getLogger(CustomerCustomResource.class.getName());

    /**
     * Retrieves a paginated search and sets the total page count of the last search
     * in the response header X-Total-Count.
     */
    @GET
    public Response get(@QueryParam("page") @DefaultValue("0") int pageIndex,
                                        @QueryParam("size") @DefaultValue("3") int pageSize) {

        List<Customer> customerList = Customer.findAll(pageIndex, pageSize);

        return Response.ok(customerList)
                .header("X-Total-Count", Customer.getPageCount())
                .build();
    }
}
