package com.product.adapter.in.web;

import com.product.adapter.out.persistence.InMemoryRepository;
import com.product.adapter.out.persistence.ProductRepositoryImplementation;
import com.product.adapter.out.persistence.utils.*;
import com.product.application.service.ProductService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("/product")
public class ProductController {

    private ProductService service;

//    public ProductController(){
//        this.service = new ProductService(new ProductRepositoryImplementation(new DB()));
//    }

    public ProductController(){
        this.service = new ProductService(new InMemoryRepository());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll(){

        return Response.ok(service.find()).build();
    }

    @GET
    @Path("market/{name}")
    public Response filterMarket(@PathParam("name") String name){
        return Response.ok(service.findMarket(name)).build();
    }

    @GET
    @Path("technology")
    public Response filterTechnology(@QueryParam("arr") List<String> name){
        return Response.ok(service.findTechnology(name)).build();
    }

}
