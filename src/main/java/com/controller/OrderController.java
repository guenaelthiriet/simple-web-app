package com.controller;

import com.dao.CustomerOrder;
import com.dao.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

//@Component
@Path("/order")
@Produces(MediaType.APPLICATION_JSON)
public class OrderController {

    @Inject
    private OrderRepository orders;

    @Context
    private UriInfo uriInfo;

    @GET
    public Page<CustomerOrder> findAll(
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("size") @DefaultValue("20") int size) {

        return orders.findAll(
                new PageRequest(
                        page,
                        size
                )
        );
    }

    @GET
    @Path("{id}")
    public CustomerOrder findOne(@PathParam("id") Long id) {
        return orders.findOne(id);
    }

    @POST
    public Response save(CustomerOrder order) {

        order = orders.save(order);

        URI location = uriInfo.getAbsolutePathBuilder()
                .path("{id}")
                .resolveTemplate("id", order.getId())
                .build();

        return Response.created(location).build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id) {
        orders.delete(id);
        return Response.accepted().build();
    }
}
