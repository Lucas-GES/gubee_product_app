package com.product.application.service;


import com.product.adapter.out.persistence.ProductRepositoryImplementation;
import com.product.adapter.out.persistence.utils.DB;
import com.product.application.port.in.ProductRepository;
import com.product.domain.Product;

import java.security.ProtectionDomain;
import java.sql.Connection;
import java.util.List;

public class ProductService {

    private ProductRepositoryImplementation repository;
    private Connection conn;

    public ProductService(){}

    public ProductService(DB db){
        repository = new ProductRepositoryImplementation(db.getConnection());
    }

    public List<Product> find(){
        return repository.findAll();
    }

    public List<Product> findMarket(String name){
        return repository.filterMarket(name);
    }

    public List<Product> findTechnology(List<String> names){
        return repository.filterTechnologies(names);
    }

}
