package com.product.application.services;


import com.product.application.repositories.ProductRepository;
import com.product.application.resources.ProductResource;
import com.product.db.DB;

public class ProductFactory {

    public static ProductRepository createProduct(){

        return new ProductResource(DB.getConnection());

    }

}
