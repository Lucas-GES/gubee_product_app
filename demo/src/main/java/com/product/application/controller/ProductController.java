package com.product.application.controller;

import com.product.application.repositories.ProductRepository;
import com.product.application.services.ProductFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("/product")
public class ProductController {

    private ProductRepository productRepository = ProductFactory.createProduct();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll(){

        return Response.ok(productRepository.findAll()).build();
    }

    @GET
    @Path("market/{name}")
    public Response filterMarket(@PathParam("name") String name){
        return Response.ok(productRepository.filterMarket(name)).build();
    }

    @GET
    @Path("technology")
    public Response filterTechnology(@QueryParam("arr") String name){
        List<String> technologies = List.of(name.split(","));
        return Response.ok(productRepository.filterTechnologies(technologies)).build();
    }

}
