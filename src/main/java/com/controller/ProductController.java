package com.controller;

import com.dao.Product;
import com.dao.ProductRepository;
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
@Path("/product")
@Produces(MediaType.APPLICATION_JSON)
public class ProductController {

    @Inject
    private ProductRepository productRepository;

    @Context
    private UriInfo uriInfo;

    @GET
    public Page<Product> findAll(
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("size") @DefaultValue("20") int size) {

        return productRepository.findAll(
                new PageRequest(
                        page,
                        size
                )
        );
    }

    @GET
    @Path("{id}")
    public Product findOne(@PathParam("id") Long id) {
        return productRepository.findOne(id);
    }

    @POST
    public Response save(Product product) {

        product = productRepository.save(product);

        URI location = uriInfo.getAbsolutePathBuilder()
                .path("{id}")
                .resolveTemplate("id", product.getId())
                .build();

        return Response.created(location).build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id) {
        productRepository.delete(id);
        return Response.accepted().build();
    }
}
