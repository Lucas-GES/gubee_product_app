package com.product.application.service;


import com.product.adapter.out.persistence.InMemoryRepository;
import com.product.adapter.out.persistence.ProductRepositoryImplementation;
import com.product.adapter.out.persistence.utils.Database;
import com.product.application.port.in.ProductRepository;
import com.product.domain.Product;

import java.util.List;

public class ProductService {

    private ProductRepository repository;

//    public ProductService(){}
//
//    public ProductService(){
//        repository = new ProductRepositoryImplementation(db.getConnection());
//    }

    public ProductService(ProductRepository productRepository){
        repository = productRepository;
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
