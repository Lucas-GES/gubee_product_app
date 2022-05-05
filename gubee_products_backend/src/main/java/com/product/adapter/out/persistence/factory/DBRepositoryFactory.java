package com.product.adapter.out.persistence.factory;

import com.product.adapter.out.persistence.ProductRepositoryImplementation;
import com.product.adapter.out.persistence.db.DB;
import com.product.application.port.in.ProductRepository;

public class DBRepositoryFactory implements RepositoryFactory{

    public ProductRepository create() {
        return new ProductRepositoryImplementation(new DB());
    }
}
